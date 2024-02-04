package com.gsm.platfra.api.services.features.like.dto;

import com.gsm.platfra.api.dto.feat.like.FeatureLikeDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeatureLikeResDto {
  private List<FeatureLikeDto> featureLikeList;
  private Integer contentLikeCount;

}
