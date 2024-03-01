package com.gsm.platfra.api.services.account.service;

import com.gsm.platfra.api.data.account.AccountDto;
import com.gsm.platfra.api.data.account.TAccount;
import com.gsm.platfra.api.data.account.TAccountDto;
import com.gsm.platfra.api.data.account.TAccountRepository;
import com.gsm.platfra.api.data.account.otp.AccountOTPDto;
import com.gsm.platfra.api.data.account.otp.TAccountOTP;
import com.gsm.platfra.api.data.account.otp.TAccountOTPRepository;
import com.gsm.platfra.api.data.platfra.PlatfraContentDto;
import com.gsm.platfra.api.data.platfra.PlatfraDto;
import com.gsm.platfra.api.data.platfra.TPlatfra;
import com.gsm.platfra.api.data.platfra.TPlatfraRepository;
import com.gsm.platfra.api.data.platfraboard.PlatfraBoardContentDto;
import com.gsm.platfra.api.services.account.dto.GoogleLoginDto;
import com.gsm.platfra.api.services.account.dto.LoginDto;
import com.gsm.platfra.api.services.account.dto.CheckOTPDto;
import com.gsm.platfra.api.services.account.dto.ResetPasswordDto;
import com.gsm.platfra.api.services.account.dto.SendCodeDto;
import com.gsm.platfra.api.services.account.dto.SignupDto;
import com.gsm.platfra.api.services.account.oauth.OauthMember;
import com.gsm.platfra.api.services.account.oauth.OauthParams;
import com.gsm.platfra.api.services.account.query.AccountOTPQueryRepository;
import com.gsm.platfra.api.services.account.query.AccountQueryRepository;
import com.gsm.platfra.api.services.board.query.PlatfraBoardContentQueryRepository;
import com.gsm.platfra.api.services.platfra.dto.ContentDto;
import com.gsm.platfra.api.services.platfra.dto.SubscribedPlatfraDto;
import com.gsm.platfra.api.services.platfra.query.PlatfraContentQueryRepository;
import com.gsm.platfra.api.services.platfra.query.PlatfraSubscribeQueryRepository;
import com.gsm.platfra.api.services.send.dto.EmailSendDto;
import com.gsm.platfra.api.services.send.service.MailSendService;
import com.gsm.platfra.exception.ExceptionCode;
import com.gsm.platfra.exception.custom.MailSendException;
import com.gsm.platfra.system.security.context.UserContextUtil;
import com.gsm.platfra.system.security.microservice.provider.AuthProvider;
import com.gsm.platfra.util.CommonUtils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountService {

    private final AuthProvider tokenProvider;
    private final TAccountRepository tAccountRepository;
    private final TPlatfraRepository tPlatfraRepository;
    private final PasswordEncoder passwordEncoder;
    private final RequestOauthInfoService requestOauthInfoService;
    private final PlatfraContentQueryRepository platfraContentQueryRepository;
    private final PlatfraBoardContentQueryRepository platfraBoardContentQueryRepository;
    private final PlatfraSubscribeQueryRepository platfraSubscribeQueryRepository;
    private final AccountQueryRepository accountQueryRepository;
    private final TAccountOTPRepository tAccountOTPRepository;
    private final AccountOTPQueryRepository accountOTPQueryRepository;
    private final MailSendService mailSendService;

    @Value("${custom.defaultFrom}")
    private String DEFAULT_FROM;

    public String login(LoginDto loginDto) {

        TAccount tAccount = tAccountRepository.findByUserId(loginDto.userId()).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 이메일입니다."));
        if (!passwordEncoder.matches( loginDto.password(), tAccount.getPassword())) {
            log.debug("비밀번호가 일치하지 않습니다.");
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return tokenProvider.generateAccessToken(tAccount);
    }

    public void signup(SignupDto signupDto) {
        isDuplicatedUserInfo(signupDto);

        TAccount entity = SignupDto.toEntity(signupDto, passwordEncoder);

        tAccountRepository.save(entity);
    }

    private void isDuplicatedUserInfo(SignupDto signupDto) {
        tAccountRepository.findByUserId(signupDto.userId()).ifPresent(tAccount -> {
            log.debug("이미 존재하는 아이디입니다.");
            throw new EntityNotFoundException("이미 존재하는 아이디입니다.");
        });

        tAccountRepository.findByEmail(signupDto.email()).ifPresent(tAccount -> {
            log.debug("이미 존재하는 이메일입니다.");
            throw new EntityNotFoundException("이미 존재하는 이메일입니다.");
        });
    }

    public String googleLogin(GoogleLoginDto googleLoginDto) {
        // Todo : 일반 회원으로 가입한 이메일인 경우 -> 예외 처리?
        TAccount tAccount = tAccountRepository.findByUserId(googleLoginDto.email()).orElseGet(
                () -> googleSignup(googleLoginDto)
        );

        return tokenProvider.generateAccessToken(tAccount);
    }

    private TAccount googleSignup(GoogleLoginDto googleLoginDto) {
        tAccountRepository.findByUserNm(googleLoginDto.username()).ifPresent(tAccount -> {
            throw new DuplicateKeyException("이미 존재하는 이름입니다.");
        });

        TAccount entity = GoogleLoginDto.toEntity(googleLoginDto);
        return tAccountRepository.save(entity);
    }

    public String oauthLogin(OauthParams oauthParams){
        OauthMember oauthMember = requestOauthInfoService.request(oauthParams);

        TAccount tAccount = tAccountRepository.findByUserId(oauthMember.getEmail()).orElseGet(
                () -> oauthSignup(oauthMember)
        );

        return tokenProvider.generateAccessToken(tAccount);
    }

    public TAccount oauthSignup(OauthMember oauthMember) {
        // TODO : oauth 회원가입 시 가입 정보 어떻게 받을 것인지?
        tAccountRepository.findByUserNm(oauthMember.getNickName()).ifPresent(tAccount -> {
            throw new DuplicateKeyException("이미 존재하는 이름입니다.");
        });
        log.debug("전달받은 유저정보:: " + oauthMember.getEmail());
        TAccount tAccount = oauthMember.toEntity(oauthMember);
        return tAccountRepository.save(tAccount);
    }

    public List<PlatfraDto> getMyPlatfra() {
        String userId = UserContextUtil.getUserContext().getUserId();
        TAccount tAccount = tAccountRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException(ExceptionCode.NOT_FOUND_USER.getMessage()));

        List<TPlatfra> myPlatforms = tPlatfraRepository.findAllByOwnerId(userId);
        List<PlatfraDto> response = myPlatforms.stream()
                .map(PlatfraDto::of)
                .toList();

        return response;
    }

    public SubscribedPlatfraDto getSubscribedPlatfra() {
        String userId = UserContextUtil.getUserContext().getUserId();
        TAccount tAccount = tAccountRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException(ExceptionCode.NOT_FOUND_USER.getMessage()));

        List<PlatfraDto> subscribedPlatfra = platfraSubscribeQueryRepository.getSubscribedPlatfra(userId);
        return SubscribedPlatfraDto.of(TAccountDto.of(tAccount), subscribedPlatfra);
    }

    public ContentDto getContents(Pageable mycontentsPageable, Pageable boardContentsPageable) {
        String userId = UserContextUtil.getUserContext().getUserId();

        List<PlatfraContentDto> myContents = platfraContentQueryRepository.getMyContents(userId, mycontentsPageable);
        List<PlatfraBoardContentDto> myBoardContent = platfraBoardContentQueryRepository.getMyBoardContent(userId, boardContentsPageable);

        return ContentDto.of(myContents, myBoardContent);
    }

    @Transactional
    public void addInfo(@Valid AccountDto accountDto) {
        accountQueryRepository.updateInfo(accountDto);
    }

    public AccountDto getAccount(String userId) {
        return accountQueryRepository.findById(userId);
    }

    @Transactional
    public boolean resetPw(ResetPasswordDto resetPasswordDto){

        TAccount account = tAccountRepository.findByUserId(resetPasswordDto.getUserId())
            .orElseThrow(()->new EntityNotFoundException("존재하지 않는 아이디 입니다."));

        /* 비밀번호, 확인비밀번호 체크 */
        if(!resetPasswordDto.samePwCheck()) return Boolean.FALSE;

        // Todo: 인증번호 제한 시간에 대한 정책이 필요함
        /* 코드 인증 확인 */
        if(!accountOTPQueryRepository.checkOTP(resetPasswordDto.getUserId())) return Boolean.FALSE;

        /* 비밀번호 변경 */
        account.update(resetPasswordDto.getPassword(), passwordEncoder);
        tAccountRepository.saveAndFlush(account);

        return Boolean.TRUE;
    }

    @Transactional
    public void sendCode(AccountOTPDto accountOTPDto) throws MailSendException {

        /* 아이디 검증 */
        if(this.getAccount(accountOTPDto.getUserId()) == null)
            throw new EntityNotFoundException("존재하지 않는 아이디 입니다.");

        String otp = CommonUtils.getGenerateCode();

        SendCodeDto sendCodeDto = SendCodeDto
            .builder()
            .otp(otp)
            .userId(accountOTPDto.getUserId())
            .email(accountOTPDto.getEmail())
            .build();

        /* 인증 코드 이메일 발송 */
        Map<String,Object> model = new HashMap<>();
        model.put("otp",sendCodeDto.getOtp());

        EmailSendDto emailSendDto = EmailSendDto
            .builder()
            .from(DEFAULT_FROM)
            .to(sendCodeDto.getEmail())
            .subject("비밀번호 초기화 메일")
            .model(model)
            .templateName("email_find_pw.ftl")
            .build();

        mailSendService.sendEmail(emailSendDto);

        /* 이메일 발송 히스토리 저장 */
        TAccountOTP tAccountOTP = TAccountOTP.builder()
            .otp(otp)
            .userId(accountOTPDto.getUserId())
            .email(accountOTPDto.getEmail())
            .build();

        tAccountOTPRepository.save(tAccountOTP);
    }


    @Transactional
    public boolean checkPasswordOTP(CheckOTPDto checkOTPDto){

        /* 아이디 검증 */
        if(this.getAccount(checkOTPDto.getUserId()) == null)
            throw new EntityNotFoundException("존재하지 않는 아이디 입니다.");

        AccountOTPDto accountOTPDto = accountOTPQueryRepository.findByUserIdLimit1(
            checkOTPDto.getUserId());

        if(accountOTPDto == null){
            throw new EntityNotFoundException("유효한 OTP 가 없습니다.");
        }

        if(!accountOTPDto.getOtp().equals(checkOTPDto.getOtp())) return false;

        TAccountOTP tAccountOTP = tAccountOTPRepository.findById(accountOTPDto.getOtpSeq())
            .orElseThrow(()->new EntityNotFoundException("OTP 관련 엔티티가 없습니다."));
        tAccountOTP.update();
        tAccountOTPRepository.saveAndFlush(tAccountOTP);

        return true;
    }

}
