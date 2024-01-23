package com.gsm.platfra.api.services.account.rest;

import com.gsm.platfra.api.services.account.dto.GoogleLoginDto;
import com.gsm.platfra.api.services.account.dto.LoginDto;
import com.gsm.platfra.api.services.account.dto.SignupDto;
import com.gsm.platfra.api.services.account.service.AccountService;
import com.gsm.platfra.api.services.account.openfeign.GoogleLogin;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static com.gsm.platfra.common.codes.ResponseCode.SUCCESS;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AccountController {

    private final AccountService accountService;
    private final GoogleLogin googleLogin;

    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginDto loginDto) {
        String token = accountService.login(loginDto);
//        HttpHeaders httpHeaders = HttpHeaderUtils.addAuthorizationToken(token);
        // Todo ) 김승동 : 토큰은 헤더에 담아서 보낼까요?
        return token;
    }

    @PostMapping("/signup")
    public String signup(@RequestBody @Valid SignupDto signupDto) {
        accountService.signup(signupDto);
        return SUCCESS.toString();
    }

    @GetMapping("/google")
    public String googleLogin(String accessToken) {
        GoogleLoginDto googleLoginDto = googleLogin.googleLogin(accessToken);
        String token = accountService.googleLogin(googleLoginDto);
        return token;
    }
}
