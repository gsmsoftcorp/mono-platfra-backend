package com.gsm.platfra.api.data.common.code;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonCodeDto {
    private String commonCd;
    private String parentCd;
    private String name;
    private String description;
    private String type;
    private String order;
    
    @Builder.Default
    private List<CommonCodeDto> childCodeDtoList = new ArrayList<>();
    
    public static TCommonCode toEntity(CommonCodeDto dto) {
        return TCommonCode.builder()
            .commonCd(dto.commonCd)
            .parentCd(dto.parentCd)
            .name(dto.name)
            .description(dto.description)
            .type(dto.type)
            .order(dto.order)
            .build();
    }
    
    public static CommonCodeDto of(@NonNull TCommonCode tCommonCode) {
        return CommonCodeDto.builder()
            .commonCd(tCommonCode.getCommonCd())
            .parentCd(tCommonCode.getParentCd())
            .name(tCommonCode.getName())
            .description(tCommonCode.getDescription())
            .type(tCommonCode.getType())
            .order(tCommonCode.getOrder())
            .build();
    }
    
}
