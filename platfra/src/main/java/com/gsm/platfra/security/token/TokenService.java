package com.gsm.platfra.security.token;

import com.gsm.platfra.api.entity.account.TAccount;
import com.gsm.platfra.security.jwt.JwtTokenizer;
import com.gsm.platfra.security.refresh.RefreshToken;
import com.gsm.platfra.security.refresh.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenizer jwtTokenizer;

    // Access Token을 생성하는 구체적인 로직
    public String delegateAccessToken(TAccount tAccount) {
        String email = tAccount.getEmail();

        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        claims.put("roles", tAccount.getRoles());
        claims.put("nickName", tAccount.getNickName());

        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());

        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String accessToken = jwtTokenizer.generateAccessToken(claims, email, expiration, base64EncodedSecretKey);

        return "Bearer " + accessToken;
    }

    // Refresh Token을 생성하는 구체적인 로직
    public String delegateRefreshToken(TAccount tAccount) {
        String subject = tAccount.getEmail();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String refreshToken = jwtTokenizer.generateRefreshToken(subject, expiration, base64EncodedSecretKey);

        RefreshToken rtk = new RefreshToken(refreshToken, tAccount.getId());
        refreshTokenRepository.save(rtk);

        return "Bearer " + refreshToken;
    }
}
