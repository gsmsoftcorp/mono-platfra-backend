package com.gsm.platfra.api.services.platfra.dto;

import com.gsm.platfra.api.data.platfra.TPlatfra;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlatformDto {

    private Long platfraSeq;
    private String platfraId;
    private String subject;
    private String description;
    private String introduction;
    private String ownerId;

    public static PlatformDto from(TPlatfra platfra) {
        return PlatformDto.builder()
                .platfraSeq(platfra.getPlatfraSeq())
                .platfraId(platfra.getPlatfraId())
                .description(platfra.getDescription())
                .introduction(platfra.getIntroduction())
                .subject(platfra.getSubject())
                .ownerId(platfra.getOwnerId())
                .build();
    }
}
