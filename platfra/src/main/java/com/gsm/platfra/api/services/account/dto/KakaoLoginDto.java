package com.gsm.platfra.api.services.account.dto;

import com.gsm.platfra.api.data.account.TAccount;

public record KakaoLoginDto(
        String username,
        String email
) {

    public static TAccount toEntity(KakaoLoginDto dto) {
        return TAccount.builder()
                .userId(dto.email)
                .email(dto.email)
                .userNm(dto.username)
                .build();
    }
}
