package com.gsm.platfra.api.services.account.oauth;

// 로그인 형태에 따른 동작을 위한 인터 페이스
public interface OauthClient {
    OauthProvider oauthProvider();
    String getOauthLoginToken(OauthParams oauthParams);
    OauthMember getMemberInfo(String accessToken);
}