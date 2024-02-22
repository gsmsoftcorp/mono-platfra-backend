package com.gsm.platfra.api.services.account.rest;

<<<<<<< HEAD
import com.gsm.platfra.api.data.platfra.PlatfraDto;
=======
import com.gsm.platfra.api.data.account.AccountDto;
>>>>>>> ce0605e (OAuth 가입 이후 추가정보 입력)
import com.gsm.platfra.api.data.platfra.saved.ContentSaveDto;
import com.gsm.platfra.api.services.account.dto.GoogleLoginDto;
import com.gsm.platfra.api.services.account.dto.LoginDto;
import com.gsm.platfra.api.services.account.dto.SignupDto;
import com.gsm.platfra.api.services.account.oauth.kakao.KakaoParams;
import com.gsm.platfra.api.services.account.openfeign.GoogleLogin;
import com.gsm.platfra.api.services.account.service.AccountService;
import com.gsm.platfra.api.services.platfra.dto.ContentDto;
import com.gsm.platfra.api.services.platfra.dto.SubscribedPlatfraDto;
import com.gsm.platfra.api.services.platfra.service.ContentSaveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("/user/{userId}/platfra")
    public List<PlatfraDto> getMyPlatfra(@PathVariable String userId) {
        return accountService.getMyPlatfra();
    }

    @GetMapping("/user/{userId}/subscribe")
    public SubscribedPlatfraDto getMySubscribe(@PathVariable String userId) {
        return accountService.getSubscribedPlatfra();
    }

    @GetMapping("/user/{userId}contents")
    public ContentDto getContents(@PathVariable String userId, Pageable contentsPageable, Pageable boardContentsPageable) {
        return accountService.getContents(contentsPageable, boardContentsPageable);
    }
    @PostMapping("/addinfo")
    public void addInfo(@RequestBody AccountDto accountDto) {
        accountService.addInfo(accountDto);
    }

    @GetMapping("/account/{userId}")
    public AccountDto getAccount(@PathVariable String userId){
        //TODO : 토큰 정보에 맞는 유저가 접근하는 지 확인 로직 필요
        return accountService.getAccount(userId);
    }


}
