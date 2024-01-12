package com.gsm.platfra.security.refresh;

import com.gsm.platfra.api.entity.account.TAccount;
import com.gsm.platfra.api.entity.account.repository.TAccountRepository;
import com.gsm.platfra.security.jwt.JwtTokenizer;
import com.gsm.platfra.security.token.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/refresh")
@RequiredArgsConstructor
public class RefreshController {
    private final JwtTokenizer jwtTokenizer;
    private final TAccountRepository tAccountRepository;
    private final TokenService tokenService;
    private final RefreshTokenRepository refreshTokenRepository;


    /**
     * 리프레쉬 토큰 받으면 엑세스 토큰 재발급
     */
    @PostMapping
    public ResponseEntity<String> refreshAccessToken(HttpServletRequest request) {
        String refreshTokenHeader = request.getHeader("Refresh");
        if (refreshTokenHeader != null && refreshTokenHeader.startsWith("Bearer ")) {
            String refreshToken = refreshTokenHeader.substring(7);
            try {
                Optional<RefreshToken> refreshTokenObj = refreshTokenRepository.findById(refreshToken);
                if (!refreshTokenObj.isPresent()) {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token [redis]");
                }

                Jws<Claims> claims = jwtTokenizer.getClaims(refreshToken, jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey()));

                String email = claims.getBody().getSubject();
                Optional<TAccount> optionalTAccount = tAccountRepository.findByEmail(email);

                if (optionalTAccount.isPresent()) {
                    TAccount tAccount = optionalTAccount.get();
                    String accessToken = tokenService.delegateAccessToken(tAccount);

                    return ResponseEntity.ok().header("Authorization", "Bearer " + accessToken).body("Access token refreshed");
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid tAccount email");
                }
            } catch (JwtException e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing refresh token");
        }
    }

    /**
     * 로그아웃할 때 리프래쉬 토큰을 삭제
     */
    @DeleteMapping
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String refreshTokenHeader = request.getHeader("Refresh");
        if (refreshTokenHeader != null && refreshTokenHeader.startsWith("Bearer ")) {
            String refreshToken = refreshTokenHeader.substring(7);
            try {
                Optional<RefreshToken> refreshTokenObj = refreshTokenRepository.findById(refreshToken);
                if (!refreshTokenObj.isPresent()) {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token [redis]");
                }

                refreshTokenRepository.deleteById(refreshToken);
                return ResponseEntity.ok().body("Logged out successfully, refresh token deleted");

            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting refresh token");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing refresh token");
        }
    }
}
