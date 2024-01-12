package com.gsm.platfra.exception;

import lombok.Getter;

public enum ExceptionCode {
    /**
     * TAccount(사용자) 관련 에러 코드
     */
    AUTHENTICATION_NOT_NULL_ALLOWED(400, "인증 정보가 필요한 요청입니다."),
    TACCOUNT_NICKNAME_NOT_ALLOWED(403, "해당 닉네임은 금지되어있습니다!!"),
    TACCOUNT_NOT_FOUND(404, "사용자를 찾을수 없습니다."),
    DUPLICATED_EMAIL(409, "이메일을 찾을수 없습니다."),
    TACCOUNT_EXISTS_EMAIL(409, "이메일이 이미 존재"),
    TACCOUNT_EXISTS_NICKNAME(409, "닉네임이 이미 존재"),
    TACCOUNT_PASSWORD_ERROR(400, "잘못된 비밀번호"),
    TACCOUNT_ALREADY_ACTIVE(409, "회원이 이미 active 상태입니다."),

    /**
     * 인증 관련 에러 코드
     */
    INVALID_TOKEN(401, "유효하지 않은 토큰입니다."),
    INVALID_PERMISSION(403, "권한이 유효하지 않습니다."),
    INACTIVE_TACCOUNT(403, "탈퇴한 회원입니다."),
    
    /**
     * 기타 에러 코드
     */
    INVALID_PROVIDER(400, "지원하지 않는 인증 제공자입니다."),
    INTERNAL_SERVER_ERROR(500, "내부 서버 오류"),
    ;

    @Getter
    private int status;
    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
