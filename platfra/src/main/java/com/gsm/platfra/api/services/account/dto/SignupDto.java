package com.gsm.platfra.api.services.account.dto;

import com.gsm.platfra.api.entity.account.TAccount;
import com.gsm.platfra.common.util.DateUtils;
import jakarta.validation.constraints.*;

public record SignupDto(
    @NotBlank(message = "아이디를 입력해주세요.")
    String userId,
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    String email,
    @Pattern(regexp = "\\d+(-\\d+)*", message = "핸드폰 번호가 올바르지 않습니다.")
    @Size(min = 10, max = 11, message = "핸드폰 번호가 올바르지 않습니다.")
    String phone,

    @NotNull
    @Size(min = 8, max = 64)
    String password,
    @Size(max = 64)
    String username,
    int age,
    Character gender,
    String birthdate
) {

    public static TAccount toEntity(SignupDto dto) {
        return TAccount.builder()
                .userId(dto.userId)
                .email(dto.email)
                .password(dto.password)
                .phone(dto.phone)
                .userNm(dto.username)
                .gender(dto.gender)
                .birthday(DateUtils.toLocalDate(dto.birthdate))
                .build();

    }
}
