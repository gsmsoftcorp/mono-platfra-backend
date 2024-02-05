package com.gsm.platfra.api.data.feature.like;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeatureLikeDto {
    //TODO com.gsm.platfra.api.data.feature.like 패키지로 옮겨주세요.
    private Long featureLikeSeq;
    private String contentsCd;
    private Long contentsSeq;
    private String userId;
    private Boolean likeYn;
    private String regUserId;
    private String modUserId;
}
