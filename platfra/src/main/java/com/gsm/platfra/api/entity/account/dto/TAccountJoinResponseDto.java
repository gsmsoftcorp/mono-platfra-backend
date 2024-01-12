package com.gsm.platfra.api.entity.account.dto;

import com.gsm.platfra.api.entity.account.TAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TAccountJoinResponseDto {
    private String uuid;
    private String email;
    private String profileImage;
    private String nickName;
    private TAccount.TAccountStatus tAccountStatus;
    private List<String> roles;
}
