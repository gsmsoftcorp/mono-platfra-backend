package com.gsm.platfra.api.data.account.info;

import com.gsm.platfra.api.data.account.TAccount;
import com.gsm.platfra.api.data.common.file.TCommonFile;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountInfoDto {

    private Long accountInfoSeq;
    private TAccount user;
    private TCommonFile profile;
    private String message;

    public static TAccountInfo toEntity(AccountInfoDto dto) {
        return TAccountInfo.builder()
                .accountInfoSeq(dto.accountInfoSeq)
                .user(dto.user)
                .profile(dto.profile)
                .message(dto.message)
                .build();
    }

    public static AccountInfoDto of(TAccountInfo tAccountInfo) {
        return AccountInfoDto.builder()
                .accountInfoSeq(tAccountInfo.getAccountInfoSeq())
                .user(tAccountInfo.getUser())
                .profile(tAccountInfo.getProfile())
                .message(tAccountInfo.getMessage())
                .build();
    }
}
