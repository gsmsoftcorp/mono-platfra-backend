package com.gsm.platfra.api.services.account.dto;

import com.gsm.platfra.api.data.account.TAccount;
import com.gsm.platfra.common.util.DateUtils;
import jakarta.validation.constraints.*;
import org.springframework.security.crypto.password.PasswordEncoder;

public record SignupDto( // TODO 멤버필드 접근제한자 private 설정필요, 기본타입 Wrapper 클래스로 통일
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
        TAccount tAccount = TAccount.builder()
            .userId(dto.userId)
            .email(dto.email)
            .password(passwordEncoder.encode(dto.password))
            .phone(dto.phone)
            .userNm(dto.username)
            .gender(dto.gender)
            .birthday(DateUtils.toLocalDate(dto.birthdate))
            .build();
        
        tAccount.setRegUserId(dto.userId);
        tAccount.setModUserId(dto.userId);
        return tAccount;
    }
}
