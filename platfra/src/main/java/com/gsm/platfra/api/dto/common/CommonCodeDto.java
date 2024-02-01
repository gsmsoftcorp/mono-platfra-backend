package com.gsm.platfra.api.dto.common;

import com.gsm.platfra.api.entity.common.TCommonCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CommonCodeDto {
    private String commonCd;
    private String parentCd;
    private String name;
    private String description;

    private static TCommonCode toEntity(CommonCodeDto dto) {
        return TCommonCode.builder()
                .commonCd(dto.commonCd)
                .parentCd(dto.parentCd)
                .name(dto.name)
                .description(dto.description)
                .build();
    }

    private static CommonCodeDto of(TCommonCode tCommonCode) {
        return CommonCodeDto.builder()
                .commonCd(tCommonCode.getCommonCd())
                .parentCd(tCommonCode.getParentCd())
                .name(tCommonCode.getName())
                .description(tCommonCode.getDescription())
                .build();
    }
}
