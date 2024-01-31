package com.gsm.platfra.common.codes;

import lombok.Getter;

@Getter
public enum ErrorCode {

    METHOD_NOT_ALLOWED("E001"),
    BIND_EXCEPTION("E002"),
    DATA_INTEGRITY_VIOLATION_EXCEPTION("E003"),
    ;

    private final String code;

    ErrorCode(String code) {
        this.code = code;
    }
}
