package com.gsm.platfra.api.services.platfra.rest;

import com.gsm.platfra.api.services.platfra.dto.account.LoginDto;
import com.gsm.platfra.api.services.platfra.dto.account.SignupDto;
import com.gsm.platfra.api.services.platfra.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.gsm.platfra.common.codes.ResponseCode.SUCCESS;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginDto loginDto) {
        String token = accountService.login(loginDto);
//        HttpHeaders httpHeaders = HttpHeaderUtils.addAuthorizationToken(token);
        // Note: 토큰도 헤더에 안담고 body에 리턴하나요....?
        return token;
    }

    @PostMapping("/signup")
    public String signup(@RequestBody @Valid SignupDto signupDto) {
        accountService.signup(signupDto);
        return SUCCESS.toString();
    }
}
