package com.gsm.platfra.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    HTTP_METHOD_NOT_SUPPORTED("HR001", "지원하지 않는 HTTP Method 입니다."),
    VALIDATION_PARAMETER_ERROR("VR001", "파라미터가 유효하지 않습니다."),
    DATA_INTEGRITY_VIOLATION_EXCEPTION("DR001", "DB 데이터 베이스 무결성을 위반했습니다."),


    AUTHENTICATION_FAILED("AR001", "인증에 실패하였습니다."),
    AUTHORIZATION_FAILED("AR002", "권한이 없습니다."),

    ENTITY_NOT_FOUND("ER001", "엔티티가 존재하지 않습니다."),
    BUSINESS_LOGIC_ERROR("BR001", "비즈니스 로직 실행 중 오류가 발생했습니다."),
    SERVER_ERROR("SR001", "서버 오류가 발생했습니다."),

    NOT_FOUND_PLATFORM("TT", "해당 플랫폼이 존재하지 않습니다."),
    NOT_FOUND_USER("TU", "해당 유저가 존재하지 않습니다."),
    NOT_FOUND_CONTENT("TC", "해당 컨텐츠가 존재하지 않습니다."),
    NOT_FOUND_BOARD("TB", "해당 게시판이 존재하지 않습니다."),

    MESSAGING_FAIL("MS001","메일 발송에 문제가 생겼습니다."),
    TEMPLATE_NOT_FOUND("MS002", "템플릿을 찾지 못하였습니다."),
    IO_ERROR("MS003","입출력 시 문제가 발생하였습니다."),
    TEMPLATE_ERROR("MS004","템플릿 에러가 발생하였습니다.")


    ;
    private final String code;
    private final String message;

    ExceptionCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
