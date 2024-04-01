package com.gsm.platfra.api.services.account.oauth.kakao;

import com.gsm.platfra.api.services.account.oauth.OauthParams;
import com.gsm.platfra.api.services.account.oauth.OauthProvider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Getter
@AllArgsConstructor
public class KakaoParams implements OauthParams {
	// Controller에서 Post요청으로 전달된 파라미터
	private String authorizationCode;

	@Override
	public OauthProvider oauthProvider() {
		return OauthProvider.KAKAO; // Enum 자료형 지정
	}

	@Override
	public MultiValueMap<String, String> makeBody() {
		// 필수로 포함되어야할 Body 작성
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("code", authorizationCode);
		return body;
	}

	@Override
	public String getAuthorizationCode() {
		return authorizationCode;
	}
}
