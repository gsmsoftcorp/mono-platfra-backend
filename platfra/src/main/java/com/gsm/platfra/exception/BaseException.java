package com.gsm.platfra.exception;

import lombok.Getter;

/**
 * BaseException 또는 BaseException 을 확장한 Exception 은
 * 서비스 운영에서 예상이 가능한 Exception 을 표현한다.
 *
 * 그렇기 때문에 http status: 200 이면서 result: FAIL 을 표현하고
 * 특정 ErrorCode 만 alert 를 포함한 모니터링 대상으로 한다.
 */
@Getter
public class BaseException extends RuntimeException {

    private ExceptionCode exceptionCode;

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(ExceptionCode errorCode) {
        super(errorCode.getMessage());
        this.exceptionCode = errorCode;
    }

    public BaseException(String message, ExceptionCode errorCode) {
        super(message);
        this.exceptionCode = errorCode;
    }

    public BaseException(String message, ExceptionCode errorCode, Throwable cause) {
        super(message, cause);
        this.exceptionCode = errorCode;
    }
}

