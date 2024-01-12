package com.gsm.platfra.security.refresh;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;


@RedisHash(value = "refreshToken", timeToLive = 25200)
public class RefreshToken {

    @Id
    private String rtk;
    private Long tAccountId;

    public RefreshToken(final String rtk, final Long tAccountId) {
        this.rtk = rtk;
        this.tAccountId = tAccountId;
    }

    public String getRefreshToken() {
        return rtk;
    }

    public Long getTAccountId() {
        return tAccountId;
    }
}
