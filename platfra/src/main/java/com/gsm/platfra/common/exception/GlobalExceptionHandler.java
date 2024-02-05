package com.gsm.platfra.common.exception;

import com.gsm.platfra.common.exception.custom.BusinessLogicException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static com.gsm.platfra.common.exception.ExceptionCode.*;

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
                HTTP_METHOD_NOT_SUPPORTED.getCode(),
                HTTP_METHOD_NOT_SUPPORTED.getMessage(),
                e.getMessage()
        );
    }

    /**
     * javax.validation.Valid 또는 @Validated binding error가 발생할 경우
     */
    @ExceptionHandler({BindException.class})
    protected ExceptionResponse handleBindException(BindException e) {
        return new ExceptionResponse(
                VALIDATION_PARAMETER_ERROR.getCode(),
                VALIDATION_PARAMETER_ERROR.getMessage(),
                createValidationMessage(e.getBindingResult())
        );
    }

    @ExceptionHandler({IllegalArgumentException.class})
    protected ExceptionResponse handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("IllegalArgumentException", e);
        return new ExceptionResponse(
                VALIDATION_PARAMETER_ERROR.getCode(),
                VALIDATION_PARAMETER_ERROR.getMessage(),
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
                DATA_INTEGRITY_VIOLATION_EXCEPTION.getCode(),
                DATA_INTEGRITY_VIOLATION_EXCEPTION.getMessage(),
                e.getMessage()
        );
    }

    /**
     * 엔티티가 존재하지 않을 때 오류 발생
     */
    @ExceptionHandler({EntityNotFoundException.class})
    protected ExceptionResponse handleEntityNotFoundException(EntityNotFoundException e) {
        log.error("EntityNotFoundException", e);
        return new ExceptionResponse(
                ENTITY_NOT_FOUND.getCode(),
                ENTITY_NOT_FOUND.getMessage(),
                e.getMessage()
        );
    }

    /**
     *  이외의 비즈니스 로직 실행 중 오류 발생한 경우
     */
    @ExceptionHandler({BusinessLogicException.class})
    protected ExceptionResponse handleBusinessLogicException(BusinessLogicException e) {
        log.error("BusinessLogicException", e);
        return new ExceptionResponse(
                BUSINESS_LOGIC_ERROR.getCode(),
                BUSINESS_LOGIC_ERROR.getMessage(),
                e.getMessage()
        );
    }

    @ExceptionHandler({Exception.class})
    protected ExceptionResponse handleException(Exception e) {
        log.error("Exception", e);
        return new ExceptionResponse(
                SERVER_ERROR.getCode(),
                SERVER_ERROR.getMessage(),
                e.getMessage()
        );
    }



    private static String createValidationMessage(BindingResult bindingResult) {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            if(!isFirst) {
                sb.append(", ");
            } else {
                isFirst = false;
            }
            sb.append("[");
            sb.append(fieldError.getField());
            sb.append("] ");
            sb.append(fieldError.getDefaultMessage());
        }

        return sb.toString();
    }


}
