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
    private TAccount tAccount;
    private String userId;
    private TCommonFile tCommonFile;
    private Long fileSeq;
    private String message;

    public static TAccountInfo toEntity(AccountInfoDto dto) {
        return TAccountInfo.builder()
                .accountInfoSeq(dto.accountInfoSeq)
                .userId(dto.userId)
                .fileSeq(dto.fileSeq)
                .message(dto.message)
                .build();
    }

    public static AccountInfoDto of(TAccountInfo tAccountInfo) {
        return AccountInfoDto.builder()
                .accountInfoSeq(tAccountInfo.getAccountInfoSeq())
                .userId(tAccountInfo.getUserId())
                .fileSeq(tAccountInfo.getFileSeq())
                .message(tAccountInfo.getMessage())
                .build();
    }

    public void setUserInfo(TAccount tAccount, TCommonFile tCommonFile) {
        this.tAccount = tAccount;
        this.tCommonFile = tCommonFile;
    }
}
