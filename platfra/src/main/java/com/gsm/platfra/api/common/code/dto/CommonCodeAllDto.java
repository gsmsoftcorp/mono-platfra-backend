package com.gsm.platfra.api.common.code.dto;


import com.gsm.platfra.api.data.common.code.CommonCodeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonCodeAllDto {
    private CommonCodeDto commonCodeDto;    //기준 코드
    private List<CommonCodeDto> childCodeDtoList;  //자식 코드리스트
}
