package com.gsm.platfra.exception.custom;

import com.gsm.platfra.exception.BaseException;
import com.gsm.platfra.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class MailSendException extends BaseException {
  private String code;
  private String message;

  public MailSendException(ExceptionCode code){
    this.code = code.getCode();
    this.message = code.getMessage();
  }
}
