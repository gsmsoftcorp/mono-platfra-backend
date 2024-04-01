package com.gsm.platfra.util;

import org.springframework.http.HttpHeaders;

public class HttpHeaderUtils {

    public static HttpHeaders addAuthorizationToken(String token) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + token);
        return httpHeaders;
    }
}
