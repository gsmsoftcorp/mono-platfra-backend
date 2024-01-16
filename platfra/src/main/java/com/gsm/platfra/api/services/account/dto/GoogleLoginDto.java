package com.gsm.platfra.api.services.account.dto;

import com.gsm.platfra.api.entity.account.TAccount;

public record GoogleLoginDto(
        String username,
        String email
) {

    public static TAccount toEntity(GoogleLoginDto dto) {
        return TAccount.builder()
                .userId(dto.email)
                .userNm(dto.username)
                .build();
    }
}
