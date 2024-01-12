package com.gsm.platfra.api.entity.account.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TAccountResponseDto {
    private Long id;

    private String nickName;
    private String email;
    private String grade;
}
