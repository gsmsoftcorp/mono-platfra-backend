package com.gsm.platfra.api.services.account.service;

import com.gsm.platfra.api.data.account.TAccount;
import com.gsm.platfra.api.data.account.TAccountDto;
import com.gsm.platfra.api.data.account.TAccountRepository;
import com.gsm.platfra.api.data.platfra.*;
import com.gsm.platfra.api.data.platfraboard.PlatfraBoardContentDto;
import com.gsm.platfra.api.services.account.dto.GoogleLoginDto;
import com.gsm.platfra.api.services.account.dto.LoginDto;
import com.gsm.platfra.api.services.account.dto.SignupDto;
import com.gsm.platfra.api.services.account.oauth.OauthMember;
import com.gsm.platfra.api.services.account.oauth.OauthParams;
import com.gsm.platfra.api.services.board.query.PlatfraBoardContentQueryRepository;
import com.gsm.platfra.api.services.platfra.dto.ContentDto;
import com.gsm.platfra.api.services.platfra.dto.SubscribedPlatfraDto;
import com.gsm.platfra.api.services.platfra.query.PlatfraContentQueryRepository;
import com.gsm.platfra.api.services.platfra.query.PlatfraSubscribeQueryRepository;
import com.gsm.platfra.exception.ExceptionCode;
import com.gsm.platfra.system.security.context.UserContextUtil;
import com.gsm.platfra.system.security.microservice.provider.AuthProvider;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
