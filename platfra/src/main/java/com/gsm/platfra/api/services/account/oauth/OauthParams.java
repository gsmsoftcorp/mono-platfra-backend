package com.gsm.platfra.api.services.account.oauth;


import org.springframework.util.MultiValueMap;

public interface OauthParams {
    public OauthProvider oauthProvider();
    public String getAuthorizationCode();
    public MultiValueMap<String, String> makeBody();
}