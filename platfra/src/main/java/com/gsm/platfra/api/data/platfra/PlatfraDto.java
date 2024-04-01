package com.gsm.platfra.api.data.platfra;

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
public class PlatfraDto {
    private Long platfraSeq;
    private String platfraId;
    private String subject;
    private String description;
    private String introduction;
    private String ownerId;
    
    private List<PlatfraContentDto> platfraContentDtoList;
    
    
    public TPlatfra toEntity() {
        TPlatfra tPlatfra = TPlatfra.builder()
            .platfraSeq(this.platfraSeq)
            .platfraId(this.platfraId)
            .subject(this.subject)
            .description(this.description)
            .introduction(this.introduction)
            .introduction(this.ownerId)
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
            .ownerId(tPlatfra.getOwnerId())
//            .platfraContentDtoList(PlatfraContentDto.listOf(tPlatfra.getTPlatfraContentList()))
            .build();
        
        return platfraDto;
    }
}



