package com.gsm.platfra.common.exception.custom;

import com.gsm.platfra.common.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class BusinessLogicException extends RuntimeException {

    private final ExceptionCode exceptionCode;

    public BusinessLogicException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

}
