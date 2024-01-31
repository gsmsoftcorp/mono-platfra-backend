package com.gsm.platfra.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.gsm.platfra.common.codes.ErrorCode.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 지원하지 않은 HTTP method 호출 할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ExceptionResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("HttpRequestMethodNotSupportedException", e);
        return new ExceptionResponse(
                HttpStatus.METHOD_NOT_ALLOWED.value(),
                METHOD_NOT_ALLOWED.getCode(),
                e.getMessage()
        );
    }

    /**
     * javax.validation.Valid 또는 @Validated binding error가 발생할 경우
     */
    @ExceptionHandler({BindException.class})
    protected ExceptionResponse handleBindException(BindException e) {
        log.error("BindException", e);
        return new ExceptionResponse(
                HttpStatus.BAD_REQUEST.value(),
                BIND_EXCEPTION.getCode(),
                e.getMessage()
        );
    }

    /**
     * DB 데이터 베이스 무결성을 위반한 경우
     */
    @ExceptionHandler({DataIntegrityViolationException.class})
    protected ExceptionResponse handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error("DataIntegrityViolationException", e);
        return new ExceptionResponse(
                HttpStatus.BAD_REQUEST.value(),
                DATA_INTEGRITY_VIOLATION_EXCEPTION.getCode(),
                e.getMessage()
        );
    }

}
