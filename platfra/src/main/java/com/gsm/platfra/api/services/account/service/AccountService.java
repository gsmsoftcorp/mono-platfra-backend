package com.gsm.platfra.api.services.account.service;

import com.gsm.platfra.api.entity.account.TAccount;
import com.gsm.platfra.api.services.account.dto.LoginDto;
import com.gsm.platfra.api.services.account.dto.SignupDto;
import com.gsm.platfra.api.services.account.repository.AccountRepository;
import com.gsm.platfra.config.provider.AuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AuthProvider tokenProvider;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public String login(LoginDto loginDto) {

        TAccount tAccount = accountRepository.findByUserId(loginDto.userId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));
        if (!tAccount.getPassword().equals(loginDto.password())) {
            // Todo: 예외 처리
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String token = tokenProvider.generateAccessToken(tAccount);

        return token;
    }

    public void signup(SignupDto signupDto) {
        isDuplicatedUserInfo(signupDto);

        TAccount entity = SignupDto.toEntity(signupDto);
        entity.setPassword(passwordEncoder.encode(signupDto.password()));

        accountRepository.save(entity);
    }

    private void isDuplicatedUserInfo(SignupDto signupDto) {
        accountRepository.findByUserId(signupDto.userId()).ifPresent(tAccount -> {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        });

        accountRepository.findByEmail(signupDto.email()).ifPresent(tAccount -> {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        });
    }

//    public String googleLogin(@NotNull String token) {
//
//
//    }
}
