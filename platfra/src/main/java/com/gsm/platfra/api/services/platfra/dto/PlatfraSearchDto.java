package com.gsm.platfra.api.services.platfra.dto;

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
public class PlatfraSearchDto {
    private String platfraId;
    private String subject;
    private String description;
    private String introduction;
}
