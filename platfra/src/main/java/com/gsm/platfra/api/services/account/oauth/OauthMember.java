package com.gsm.platfra.api.services.account.oauth;

import com.gsm.platfra.api.data.account.TAccount;

// Oauth 로그인을 통한 멤버들이 구현할 인터페이스
public interface OauthMember {
    public String getEmail();
    public String getNickName();
    OauthProvider getOauthProvider();

    TAccount toEntity(OauthMember oauthMember);
}

