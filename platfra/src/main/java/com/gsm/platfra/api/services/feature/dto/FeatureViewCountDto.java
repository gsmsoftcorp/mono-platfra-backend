package com.gsm.platfra.api.services.feature.dto;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class FeatureViewCountDto {

    private String contentsCd;
    private Long contentsSeq;
    private Long count;
}
