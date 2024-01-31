package com.gsm.platfra.common.codes;

import lombok.Getter;

@Getter
public enum ErrorMessage {

    INVALID_PASSWORD("비밀번호가 일치하지 않습니다."),

    ;

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }
}

