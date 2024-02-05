package com.gsm.platfra.api.services.account.service;

import com.gsm.platfra.api.data.account.TAccount;
import com.gsm.platfra.api.data.account.TAccountRepository;
import com.gsm.platfra.api.services.account.dto.GoogleLoginDto;
import com.gsm.platfra.api.services.account.dto.LoginDto;
import com.gsm.platfra.api.services.account.dto.SignupDto;
import com.gsm.platfra.system.security.provider.AuthProvider;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountService {

    private final AuthProvider tokenProvider;
    private final TAccountRepository TAccountRepository;
    private final PasswordEncoder passwordEncoder;

    public String login(LoginDto loginDto) {

        TAccount tAccount = TAccountRepository.findByUserId(loginDto.userId()).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 이메일입니다."));
        if (!passwordEncoder.matches( loginDto.password(), tAccount.getPassword())) {
            log.debug("비밀번호가 일치하지 않습니다.");
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return tokenProvider.generateAccessToken(tAccount);
    }

    public void signup(SignupDto signupDto) {
        isDuplicatedUserInfo(signupDto);

        TAccount entity = SignupDto.toEntity(signupDto, passwordEncoder);

        TAccountRepository.save(entity);
    }

    private void isDuplicatedUserInfo(SignupDto signupDto) {
        TAccountRepository.findByUserId(signupDto.userId()).ifPresent(tAccount -> {
            log.debug("이미 존재하는 아이디입니다.");
            throw new EntityNotFoundException("이미 존재하는 아이디입니다.");
        });

        TAccountRepository.findByEmail(signupDto.email()).ifPresent(tAccount -> {
            log.debug("이미 존재하는 이메일입니다.");
            throw new EntityNotFoundException("이미 존재하는 이메일입니다.");
        });
    }

    public String googleLogin(GoogleLoginDto googleLoginDto) {
        // Todo : 일반 회원으로 가입한 이메일인 경우 -> 예외 처리?
        TAccount tAccount = TAccountRepository.findByUserId(googleLoginDto.email()).orElseGet(
                () -> googleSignup(googleLoginDto)
        );

        return tokenProvider.generateAccessToken(tAccount);
    }

    private TAccount googleSignup(GoogleLoginDto googleLoginDto) {
        TAccountRepository.findByUserNm(googleLoginDto.username()).ifPresent(tAccount -> {
            throw new DuplicateKeyException("이미 존재하는 이름입니다.");
        });

        TAccount entity = GoogleLoginDto.toEntity(googleLoginDto);
        return TAccountRepository.save(entity);
    }

}
