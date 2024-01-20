package com.gsm.platfra.api.services.platfra.dto.table;

import com.gsm.platfra.api.entity.platfra.TPlatfra;
import com.gsm.platfra.api.entity.platfra.TPlatfraContent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlatfraDto {
    private Long platfraSeq;
    private String platfraId;
    private String subject;
    private String description;
    private String introduction;
    
    private List<PlatfraContentDto> platfraContentDtoList;
    
    
    public TPlatfra toEntity() {
        TPlatfra tPlatfra = TPlatfra.builder()
            .platfraSeq(this.platfraSeq)
            .platfraId(this.platfraId)
            .subject(this.subject)
            .description(this.description)
            .introduction(this.introduction)
            .build();
        
        return tPlatfra;
    }
    
    public static PlatfraDto of(TPlatfra tPlatfra) {
        PlatfraDto platfraDto = PlatfraDto.builder()
            .platfraSeq(tPlatfra.getPlatfraSeq())
            .platfraId(tPlatfra.getPlatfraId())
            .subject(tPlatfra.getSubject())
            .description(tPlatfra.getDescription())
            .introduction(tPlatfra.getIntroduction())
//            .platfraContentDtoList(PlatfraContentDto.listOf(tPlatfra.getTPlatfraContentList()))
            .build();
        
        return platfraDto;
    }
}



