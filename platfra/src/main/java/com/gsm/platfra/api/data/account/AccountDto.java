package com.gsm.platfra.api.data.account;

import com.gsm.platfra.api.data.common.code.CommonCodeDto;
import com.gsm.platfra.api.data.common.code.TCommonCode;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private String userId;
    private String email;
    @Pattern(regexp = "\\d+(-\\d+)*", message = "핸드폰 번호가 올바르지 않습니다.")
    @Size(min = 10, max = 11, message = "핸드폰 번호가 올바르지 않습니다.")
    private String phone;
    private String password;
    private String userNm;
    private String nickname;
    private Integer age;
    private Character gender;
    private LocalDate birthday;

    public static TAccount toEntity(AccountDto dto) {
        return TAccount.builder()
                .userId(dto.userId)
                .email(dto.email)
                .phone(dto.phone)
                .password(dto.password)
                .userNm(dto.userNm)
                .nickname(dto.nickname)
                .age(dto.age)
                .gender(dto.gender)
                .birthday(dto.birthday)
                .build();
    }

    public static AccountDto of(@NonNull TAccount tAccount) {
        return AccountDto.builder()
                .userId(tAccount.getUserId())
                .email(tAccount.getEmail())
                .phone(tAccount.getPhone())
                .password(tAccount.getPassword())
                .userNm(tAccount.getUserNm())
                .nickname(tAccount.getNickname())
                .age(tAccount.getAge())
                .gender(tAccount.getGender())
                .birthday(tAccount.getBirthday())
                .build();
    }
}
