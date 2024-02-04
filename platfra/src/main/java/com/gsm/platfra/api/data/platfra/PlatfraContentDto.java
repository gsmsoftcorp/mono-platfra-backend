package com.gsm.platfra.api.data.platfra;

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
public class PlatfraContentDto {
    private Long contentSeq;
    private String platfraId;
    private Long contentNo;
    private String title;
    private String content;
    
    public TPlatfraContent toEntity() {
        TPlatfraContent tPlatfraContent = TPlatfraContent.builder()
            .contentSeq(this.contentSeq)
            .platfraId(this.platfraId)
            .title(this.title)
            .content(this.content)
            .build();
        
        return tPlatfraContent;
    }
    public static PlatfraContentDto of(TPlatfraContent tPlatfraContent) {
        PlatfraContentDto platfraContentDto = PlatfraContentDto.builder()
            .contentSeq(tPlatfraContent.getContentSeq())
            .platfraId(tPlatfraContent.getPlatfraId())
            .contentNo(tPlatfraContent.getContentNo())
            .title(tPlatfraContent.getTitle())
            .content(tPlatfraContent.getContent())
            .build();
        
        return platfraContentDto;
    }
    public static List<PlatfraContentDto> listOf(List<TPlatfraContent> tPlatfraContentList) {
        List<PlatfraContentDto> platfraContentDtoList = new ArrayList<>();
        if(!tPlatfraContentList.isEmpty()) {
            tPlatfraContentList.forEach(tPlatfraContent -> {
                platfraContentDtoList.add(PlatfraContentDto.of(tPlatfraContent));
            });
        }
        return platfraContentDtoList;
    }
}
