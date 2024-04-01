package com.gsm.platfra.exception.custom;

import com.gsm.platfra.exception.ExceptionCode;
import com.gsm.platfra.exception.ExceptionResponse;
import jakarta.mail.MessagingException;
import lombok.Getter;

@Getter
public class MailSendException extends MessagingException {
  private String code;
  private String message;

  public MailSendException(ExceptionCode code){
    this.code = code.getCode();
    this.message = code.getMessage();
  }
}
