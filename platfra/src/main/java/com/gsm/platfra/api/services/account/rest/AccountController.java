package com.gsm.platfra.api.services.account.rest;

import com.gsm.platfra.api.data.platfra.saved.ContentSaveDto;
import com.gsm.platfra.api.services.account.dto.GoogleLoginDto;
import com.gsm.platfra.api.services.account.dto.LoginDto;
import com.gsm.platfra.api.services.account.dto.SignupDto;
import com.gsm.platfra.api.services.account.oauth.kakao.KakaoParams;
import com.gsm.platfra.api.services.account.openfeign.GoogleLogin;
import com.gsm.platfra.api.services.account.service.AccountService;
import com.gsm.platfra.api.services.platfra.service.ContentSaveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gsm.platfra.codes.ResponseCode.SUCCESS;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AccountController {

    private final AccountService accountService;
    private final ContentSaveService contentSaveService;
    private final GoogleLogin googleLogin;

    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginDto loginDto) {
        String token = accountService.login(loginDto);
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

    @GetMapping("kakao")
    public String oauthLogin(String code){
        KakaoParams kakaoParams = new KakaoParams(code);
        String token = accountService.oauthLogin(kakaoParams);
        return token;
    }

    @GetMapping("/user/{userId}/content")
    public List<ContentSaveDto> getContentList(@PathVariable String userId) {
        return contentSaveService.getMyContentList(userId);
    }


    @DeleteMapping("/user/{userId}/{contentSeq}")
    public String deleteContent(
            @PathVariable String userId,
            @PathVariable Long contentSeq) {
        contentSaveService.deleteContent(userId, contentSeq);
        return SUCCESS.toString();
    }
}
