package com.gsm.platfra.api.services.account.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LoginDto(
        @NotBlank(message = "아이디를 입력해주세요.")
        String userId,
        @NotNull
        @Size(min = 8, max = 64)
        String password
) {
}
