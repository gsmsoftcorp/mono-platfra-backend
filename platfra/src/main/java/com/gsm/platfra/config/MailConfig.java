package com.gsm.platfra.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

//    @Value("${spring.mail.host}")
//    private String host;
//    @Value("${spring.mail.port}")
//    private int port;
//    @Value("${spring.mail.username}")
//    private String userName;
//    @Value("${spring.mail.password}")
//    private String password;
//    @Value("${mail.transport.protocol}")
//    private String protocol;
//    @Value("${mail.smtp.auth}")
//    private String auth;
//    @Value("${mail.smtp.starttls.enable}")
//    private String enable;
//    @Value("${mail.debug}")
//    private String debug;

    /*
       컨트롤러 부분
       @Autowired
       private JavaMailSender javaMailSender;

       SimpleMailMessage message = new SimpleMailMessage();
       message.setFrom("a01063905445@gmail.com");
       message.setTo("szsz1230@naver.com");
       message.setSubject("피카츄라이츄파이리꼬부기");
       message.setText("안녕하세요를레이후~~");
       javaMailSender.send(message);
   */

//    @Bean
//    @Primary
//    public JavaMailSender getJavaMailSender() {
//
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost( host );
//        mailSender.setPort( port );
//        mailSender.setUsername( userName );
//        mailSender.setPassword( password );
//
//        Properties props = mailSender.getJavaMailProperties();
//        props.put("mail.transport.protocol", protocol);
//        props.put("mail.smtp.auth", auth);
//        props.put("mail.smtp.starttls.enable", enable);
//        props.put("mail.debug", debug);
//
//        return mailSender;
//    }

//    @Bean
//    public FreeMarkerConfigurer freemarkerConfig() {
//        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
//        freeMarkerConfigurer.setTemplateLoaderPath("classpath:/html_template/account/");
//        return freeMarkerConfigurer;
//    }

}
