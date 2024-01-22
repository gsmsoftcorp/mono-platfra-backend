package com.gsm.platfra.common.codes;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    INVALID_PASSWORD("비밀번호가 일치하지 않습니다."),

    ;

    private String message;

    ErrorCode(String message) {
        this.message = message;
    }
}

