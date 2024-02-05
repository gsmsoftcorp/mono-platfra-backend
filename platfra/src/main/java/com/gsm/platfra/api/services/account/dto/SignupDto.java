package com.gsm.platfra.api.services.account.dto;

import com.gsm.platfra.api.data.account.TAccount;
import com.gsm.platfra.common.util.DateUtils;
import jakarta.validation.constraints.*;
import org.springframework.security.crypto.password.PasswordEncoder;

public record SignupDto(
    @NotBlank(message = "아이디를 입력해주세요.")
    String userId,
    @NotBlank(message = "이메일을 입력해주세요")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    String email,
    @Pattern(regexp = "\\d+(-\\d+)*", message = "핸드폰 번호가 올바르지 않습니다.")
    @Size(min = 10, max = 11, message = "핸드폰 번호가 올바르지 않습니다.")
    String phone,

    @NotBlank
    @Size(min = 8, max = 64)
    String password,
    @Size(max = 64)
    String username,
    int age,
    Character gender,
    String birthdate
) {

    public static TAccount toEntity(SignupDto dto, PasswordEncoder passwordEncoder) {
        return TAccount.builder()
                .userId(dto.userId)
                .email(dto.email)
                .password(passwordEncoder.encode(dto.password))
                .phone(dto.phone)
                .userNm(dto.username)
                .gender(dto.gender)
                .birthday(DateUtils.toLocalDate(dto.birthdate))
                .build();

    }
}
