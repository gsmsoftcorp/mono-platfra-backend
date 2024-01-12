package com.gsm.platfra.security.handler;

import com.gsm.platfra.api.entity.account.TAccount;
import com.gsm.platfra.api.entity.account.repository.TAccountRepository;
import com.gsm.platfra.exception.BusinessLogicException;
import com.gsm.platfra.exception.ExceptionCode;
import com.gsm.platfra.security.jwt.JwtTokenizer;
import com.gsm.platfra.security.token.TokenService;
import com.gsm.platfra.security.utils.CustomAuthorityUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//  OAuth 2 인증 후, Frontend 애플리케이션 쪽으로 JWT를 전송하는 역할
@Slf4j
@Component
public class OAuth2TAccountSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;
    private final TAccountRepository tAccountRepository;
    private final TokenService tokenService;

    public OAuth2TAccountSuccessHandler(JwtTokenizer jwtTokenizer,
                                        CustomAuthorityUtils authorityUtils, TAccountRepository tAccountRepository, TokenService tokenService) {
        this.jwtTokenizer = jwtTokenizer;
        this.authorityUtils = authorityUtils;
        this.tAccountRepository = tAccountRepository;
        this.tokenService = tokenService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();

        OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) authentication;
        String providerType = authToken.getAuthorizedClientRegistrationId();

        String email;
        String nickName;
        String profileImage;
        TAccount.Provider provider;

        if ("google".equals(providerType)) {
            email = String.valueOf(oAuth2User.getAttributes().get("email"));
            nickName = String.valueOf(oAuth2User.getAttributes().get("name"));
            profileImage = String.valueOf(oAuth2User.getAttributes().get("picture"));
            provider = TAccount.Provider.GOOGLE;
        } else if ("kakao".equals(providerType)) {
            Map<String, Object> attributes = oAuth2User.getAttributes();
            Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
            email = (String) kakaoAccount.get("email");
            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
            nickName = (String) profile.get("nickname");
            profileImage = (String) profile.get("profile_image_url");
            provider = TAccount.Provider.KAKAO;
        } else if ("naver".equals(providerType)) {
            Map<String, Object> attributes = oAuth2User.getAttributes();
            Map<String, Object> response2 = (Map<String, Object>) attributes.get("response");
            email = (String) response2.get("email");
            nickName = (String) response2.get("nickname");
            profileImage = (String) response2.get("profile_image");
            provider = TAccount.Provider.NAVER;
        } else {
            throw new BusinessLogicException(ExceptionCode.INVALID_PROVIDER, "Unsupported provider: " + providerType);
        }

        Optional<TAccount> optionalTAccount = tAccountRepository.findByEmail(email);

        TAccount tAccount;
        if (optionalTAccount.isEmpty()) {
            tAccount = saveTAccount(email, nickName, profileImage, provider);
        } else {
            tAccount = optionalTAccount.get();
        }
        redirect(request, response, tAccount);
    }

    private TAccount saveTAccount(String email, String nickname, String profileImage, TAccount.Provider provider) {
        tAccountRepository.findByEmail(email).ifPresent(it ->
        {throw new BusinessLogicException(ExceptionCode.TACCOUNT_EXISTS_EMAIL, String.format("%s is duplicated 버그발생! OAuth2 핸들러 검사하시오.", email));
        });
        TAccount tAccount = new TAccount();
        tAccount.setEmail(email);
        tAccount.setNickName(nickname);
        tAccount.setProfileImage(profileImage);
        List<String> roles = authorityUtils.createRoles(email);
        tAccount.setRoles(roles);
        tAccount.setProvider(provider);
        TAccount savedTAccount = tAccountRepository.save(tAccount);

        return savedTAccount;
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response, TAccount tAccount) throws IOException {
        String accessToken = tokenService.delegateAccessToken(tAccount);
        String refreshToken = tokenService.delegateRefreshToken(tAccount);

        response.setHeader("Authorization", accessToken);
        response.setHeader("Refresh", refreshToken);

        String uri = createURI(accessToken, refreshToken).toString();
        getRedirectStrategy().sendRedirect(request, response, uri);

        log.info("# Authenticated successfully!");

        // response 헤더 정보 로그 출력
        for (String headerName : response.getHeaderNames()) {
            log.info(headerName + ": " + response.getHeader(headerName));
        }
    }

    private URI createURI(String accessToken, String refreshToken) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("access_token", accessToken);
        queryParams.add("refresh_token", refreshToken);

        return UriComponentsBuilder
                .newInstance()
                .scheme("https")
                .host("*")
                .path("/oauth/redirect")
                .queryParams(queryParams)
                .build()
                .toUri();
    }
}
