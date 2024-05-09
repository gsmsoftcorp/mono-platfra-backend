package com.gsm.platfra.api.services.account.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDto{
        @NotBlank(message = "아이디를 입력해주세요.")
        private String userId;
        @NotBlank(message = "비밀번호를 입력해주세요.")
        @Size(min = 8, max = 64)
        private String password;
}
