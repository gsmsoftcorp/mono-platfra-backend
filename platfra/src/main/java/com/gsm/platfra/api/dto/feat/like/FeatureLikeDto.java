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
    private String contentCd;
    private Long contentSeq;
    private String userId;
    private Boolean likeYn;
}
