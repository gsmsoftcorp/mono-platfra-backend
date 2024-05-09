package com.gsm.platfra.api.services.account.rest;

import com.gsm.platfra.api.data.account.otp.AccountOTPDto;
import com.gsm.platfra.api.data.base.BaseResponse;
import com.gsm.platfra.api.data.platfra.PlatfraDto;
import com.gsm.platfra.api.data.account.AccountDto;
import com.gsm.platfra.api.data.platfra.saved.ContentSaveDto;
import com.gsm.platfra.api.services.account.dto.GoogleLoginDto;
import com.gsm.platfra.api.services.account.dto.LoginDto;
import com.gsm.platfra.api.services.account.dto.CheckOTPDto;
import com.gsm.platfra.api.services.account.dto.ResetPasswordDto;
import com.gsm.platfra.api.services.account.dto.SignupDto;
import com.gsm.platfra.api.services.account.oauth.kakao.KakaoParams;
import com.gsm.platfra.api.services.account.openfeign.GoogleLogin;
import com.gsm.platfra.api.services.account.service.AccountService;
import com.gsm.platfra.api.services.platfra.dto.ContentDto;
import com.gsm.platfra.api.services.platfra.dto.SubscribedPlatfraDto;
import com.gsm.platfra.api.services.platfra.service.ContentSaveService;
import com.gsm.platfra.exception.custom.MailSendException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gsm.platfra.codes.ResponseCode.SUCCESS;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private final ContentSaveService contentSaveService;
    private final GoogleLogin googleLogin;

//    @PostMapping("/login")
//    public String login(@RequestBody @Valid LoginDto loginDto) {
//        String token = accountService.login(loginDto);
//        return token;
//    }
    @PostMapping("/login")
    public BaseResponse login(@RequestBody @Valid LoginDto loginDto) {
        try {
            String token = accountService.login(loginDto);
            return BaseResponse.builder()
                .data(token)
                .code(null)
                .message(null)
                .error(null)
                .build();

        } catch (AuthenticationException e) {
            return BaseResponse.builder()
                .data(null)
                .code("업무처리결과코드(공통코드 또는 공통프로퍼티)")
                .message(null)
                .error(e)
                .build();

        } catch (Exception e) {
            return BaseResponse.builder()
                .data(null)
                .code("업무처리결과코드(공통코드 또는 공통프로퍼티)")
                .message(null)
                .error(e)
                .build();
        }
    }
    @PostMapping("/signup")
    public BaseResponse signup(@RequestBody @Valid SignupDto signupDto) {
        accountService.signup(signupDto);
        return BaseResponse.builder()
            .data(SUCCESS.toString())
            .code(null)
            .message(null)
            .error(null)
            .build();
    }

    @GetMapping("/google")
    public BaseResponse googleLogin(String accessToken) {
        GoogleLoginDto googleLoginDto = googleLogin.googleLogin(accessToken);
        String token = accountService.googleLogin(googleLoginDto);
        return BaseResponse.builder()
            .data(token)
            .code(null)
            .message(null)
            .error(null)
            .build();
    }

    @GetMapping("/kakao")
    public BaseResponse oauthLogin(String code){
        KakaoParams kakaoParams = new KakaoParams(code);
        String token = accountService.oauthLogin(kakaoParams);
        return BaseResponse.builder()
            .data(token)
            .code(null)
            .message(null)
            .error(null)
            .build();
    }

    @GetMapping("/user/{userId}/content")
    public BaseResponse getContentList(@PathVariable String userId) {
        return BaseResponse.builder()
            .data(contentSaveService.getMyContentList(userId))
            .code(null)
            .message(null)
            .error(null)
            .build();
    }


    @DeleteMapping("/user/{userId}/{contentSeq}")
    public BaseResponse deleteContent(
            @PathVariable String userId,
            @PathVariable Long contentSeq) {
        contentSaveService.deleteContent(userId, contentSeq);
        return BaseResponse.builder()
            .data(SUCCESS.toString())
            .code(null)
            .message(null)
            .error(null)
            .build();
    }

    @GetMapping("/user/{userId}/platfra")
    public BaseResponse getMyPlatfra(@PathVariable String userId) {
        return BaseResponse.builder()
            .data(accountService.getMyPlatfra())
            .code(null)
            .message(null)
            .error(null)
            .build();
    }

    @GetMapping("/user/{userId}/subscribe")
    public BaseResponse getMySubscribe(@PathVariable String userId) {
        return BaseResponse.builder()
            .data(accountService.getSubscribedPlatfra())
            .code(null)
            .message(null)
            .error(null)
            .build();
    }

    @GetMapping("/user/{userId}contents")
    public BaseResponse getContents(@PathVariable String userId, Pageable contentsPageable, Pageable boardContentsPageable) {
        return BaseResponse.builder()
            .data(accountService.getContents(contentsPageable, boardContentsPageable))
            .code(null)
            .message(null)
            .error(null)
            .build();
    }
    @PostMapping("/addinfo")
    public BaseResponse addInfo(@RequestBody AccountDto accountDto) {
        accountService.addInfo(accountDto);
        return BaseResponse.builder()
            .data(null)
            .code(null)
            .message(null)
            .error(null)
            .build();
    }

    @GetMapping("/account/{userId}")
    public BaseResponse getAccount(@PathVariable String userId){
        //TODO : 토큰 정보에 맞는 유저가 접근하는 지 확인 로직 필요
        return BaseResponse.builder()
            .data(accountService.getAccount(userId))
            .code(null)
            .message(null)
            .error(null)
            .build();
    }

    /**
    * 비밀번호 초기화를 위한 otp 이메일 발송
    * @param accountOTPDto
    * @return void
    * */
    @PostMapping("/password")
    public BaseResponse sendResetPasswordCode(@RequestBody AccountOTPDto accountOTPDto) throws MailSendException {
        accountService.sendCode(accountOTPDto);
        return BaseResponse.builder()
            .data(null)
            .code(null)
            .message(null)
            .error(null)
            .build();
    }

    /**
    * 비밀번호 초기화를 위한 이메일 otp 인증
    * @param checkOTPDto
    * @return boolean
    * */
    @PutMapping("/password")
    public BaseResponse checkResetPasswordOTPCode(@RequestBody CheckOTPDto checkOTPDto){
        return BaseResponse.builder()
            .data(accountService.checkPasswordOTP(checkOTPDto))
            .code(null)
            .message(null)
            .error(null)
            .build();
    }

    /**
    * 비밀번호 초기화
    * @param resetPasswordDto
    * @return boolean
    * */
    @PatchMapping("/password")
    public BaseResponse resetPassword(@RequestBody ResetPasswordDto resetPasswordDto){
        return BaseResponse.builder()
            .data(accountService.resetPw(resetPasswordDto))
            .code(null)
            .message(null)
            .error(null)
            .build();
    }


}
