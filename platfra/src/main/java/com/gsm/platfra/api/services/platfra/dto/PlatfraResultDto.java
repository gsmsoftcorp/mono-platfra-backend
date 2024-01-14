package com.gsm.platfra.api.services.platfra.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * 출력용 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlatfraResultDto {
    //TPlatfra
    private String platfraId;
    private String subject;
    private String description;
    private String introduction;
    
    //TCategory
    private String categoryCd;
    
}
