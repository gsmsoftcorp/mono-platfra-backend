package com.gsm.platfra.api.services.platfra.dto;

import com.gsm.platfra.api.services.platfra.dto.table.PlatfraContentDto;
import com.gsm.platfra.api.services.platfra.dto.table.PlatfraDto;
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
public class PlatfraMainResDto {
    private PlatfraDto platfraDto; //플랫폼 마스터
    private List<PlatfraContentDto> latestContentList;  //최신 컨텐츠 리스트
    private List<PlatfraContentDto> hotContentList;  //인기 컨텐츠 리스트
}
