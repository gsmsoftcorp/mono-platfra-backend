package com.gsm.platfra.api.services.send.service;

import com.gsm.platfra.api.services.send.dto.EmailSendDto;
import com.gsm.platfra.exception.ExceptionCode;
import com.gsm.platfra.exception.custom.MailSendException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.StringWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class MailSendService {

  private final JavaMailSender javaMailSender;
  private final Configuration freemarkerConfiguration;

  @Value("${custom.defaultFrom}")
  private String DEFAULT_FROM;

  public void sendEmail(EmailSendDto emailSendDto) throws MailSendException{

    log.info("Send Email start : {}",emailSendDto);

    MimeMessage message = javaMailSender.createMimeMessage();

    try{

      MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");

      helper.setTo(emailSendDto.getTo());
      helper.setFrom(DEFAULT_FROM);
      helper.setSubject(emailSendDto.getSubject());

      Template template = freemarkerConfiguration.getTemplate(emailSendDto.getTemplateName());
      StringWriter stringWriter = new StringWriter();
      template.process(emailSendDto.getModel(),stringWriter);

      helper.setText(stringWriter.toString(),true);

      javaMailSender.send(message);

      log.info("End Send Email.");
    }catch(MessagingException e){
      log.error("메세징 에러 발생");
      throw new MailSendException(ExceptionCode.MESSAGING_FAIL);
    }catch(TemplateNotFoundException e){
      log.error("템플릿 에러 발생");
      throw new MailSendException(ExceptionCode.TEMPLATE_NOT_FOUND);
    }catch (IOException e){
      log.error("입출력 에러 발생");
      throw new MailSendException(ExceptionCode.IO_ERROR);
    }catch (TemplateException e){
      log.error("템플릿 에러 발생");
      throw new MailSendException(ExceptionCode.TEMPLATE_ERROR);
    }

  }
}
