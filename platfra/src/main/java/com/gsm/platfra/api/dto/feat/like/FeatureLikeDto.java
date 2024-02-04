package com.gsm.platfra.api.dto.feat.like;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeatureLikeDto {
    private Long featureLikeSeq;
    private String contentsCd;
    private Long contentsSeq;
    private String userId;
    private Boolean likeYn;
    private String regUserId;
    private String modUserId;
}