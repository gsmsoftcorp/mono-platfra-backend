package com.gsm.platfra.common.exception;

import lombok.Builder;

@Builder
public record ExceptionResponse(

        String type, // 예외 코드
        String message, // 사용자를 위한 예외 메세지
        String detail // 서버 개발자를 위한 예외 메세지
) {
}
