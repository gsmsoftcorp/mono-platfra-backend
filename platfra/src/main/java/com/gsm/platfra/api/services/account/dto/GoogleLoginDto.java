package com.gsm.platfra.api.services.account.dto;

import com.gsm.platfra.api.data.account.TAccount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GoogleLoginDto{

    private String username;
    private String email;

    public static TAccount toEntity(GoogleLoginDto dto) {
        return TAccount.builder()
                .userId(dto.email)
                .email(dto.email)
                .userNm(dto.username)
                .build();
    }
}
