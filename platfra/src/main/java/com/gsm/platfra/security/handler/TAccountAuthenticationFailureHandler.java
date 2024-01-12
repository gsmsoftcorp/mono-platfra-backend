package com.gsm.platfra.security.handler;

import com.gsm.platfra.security.utils.ErrorResponder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

@Slf4j
public class TAccountAuthenticationFailureHandler implements AuthenticationFailureHandler {  // 로그인 인증 실패 시, 추가 작업을 할 수 있는 클래스
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        log.error("# Authentication failed: {}", exception.getMessage());

        if(exception.getMessage().equals("탈퇴한 회원입니다.")){
            ErrorResponder.sendErrorResponse(response, HttpStatus.FORBIDDEN, "Member is not active");
        }else {
            log.error("# Authentication failed: Please check your email and password again.");
            ErrorResponder.sendErrorResponse(response, HttpStatus.UNAUTHORIZED, "Please check your email and password again.");
        }
    }
}
