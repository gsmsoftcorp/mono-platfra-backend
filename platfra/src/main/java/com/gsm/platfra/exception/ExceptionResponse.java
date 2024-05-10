package com.gsm.platfra.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExceptionResponse{
    private String code; // 예외 코드
    private String message; // 사용자를 위한 예외 메세지
    private String detail; // 서버 개발자를 위한 예외 메세지
}
