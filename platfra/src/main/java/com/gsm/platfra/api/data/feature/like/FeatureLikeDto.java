package com.gsm.platfra.api.data.feature.like;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "컨텐츠 종류를 입력해 주세요.")
    private String contentsCd;
    @NotNull(message = "컨텐츠 번호를 입력해 주세요.")
    private Long contentsSeq;
    private String userId;
    private Boolean likeYn;
    private String regUserId;
    private String modUserId;
}
