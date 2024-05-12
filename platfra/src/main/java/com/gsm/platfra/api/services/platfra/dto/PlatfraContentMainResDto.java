package com.gsm.platfra.api.services.platfra.dto;

import com.gsm.platfra.api.data.platfra.PlatfraContentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlatfraContentMainResDto {
    private PlatfraContentDto content; // 컨텐츠
}
