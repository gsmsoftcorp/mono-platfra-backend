package com.gsm.platfra.codes;

import lombok.Getter;

@Getter
public enum ResponseCode {
    SUCCESS("성공"),
    FAIL("실패")
    ;

    private final String msg;

    ResponseCode(String msg) {
        this.msg = msg;
    }
}
