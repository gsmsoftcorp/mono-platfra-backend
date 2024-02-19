package com.gsm.platfra.api.features.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeatureDto {
  // COMMON_CODE
  private String commonCd;

  // KEY 값이 String 인 경우
  private String featureNo;

  // KEY 값이 int 인 경우
  private Long featureSeq;

}
