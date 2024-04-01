package com.gsm.platfra.api.features.view.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeatureViewCountDto {

    private String contentsCd;
    private Long contentsSeq;
    private Long count;
}
