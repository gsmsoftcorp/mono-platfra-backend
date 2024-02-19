package com.gsm.platfra.api.data.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TAccountDto {
    private String userId;
    private String email;
    private String phone;
    private String password;
    private String userNm;
    private Integer age;
    private Character gender;
    private LocalDate birthday;
    private Boolean bannedYn;

    public static TAccountDto of(TAccount tAccount) {
        return TAccountDto.builder()
                .userId(tAccount.getUserId())
                .email(tAccount.getEmail())
                .phone(tAccount.getPhone())
                .password(tAccount.getPassword())
                .userNm(tAccount.getUserNm())
                .age(tAccount.getAge())
                .gender(tAccount.getGender())
                .birthday(tAccount.getBirthday())
                .bannedYn(tAccount.getBannedYn())
                .build();
    }
}
