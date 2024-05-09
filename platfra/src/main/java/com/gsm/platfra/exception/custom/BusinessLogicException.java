package com.gsm.platfra.exception.custom;

import com.gsm.platfra.exception.BaseException;
import com.gsm.platfra.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class BusinessLogicException extends BaseException {

    private final ExceptionCode exceptionCode;

    public BusinessLogicException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

}
