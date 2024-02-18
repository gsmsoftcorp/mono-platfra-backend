package com.gsm.platfra.api.features.dto;

import lombok.Data;

@Data
public class FeatureDto {
  // COMMON_CODE
  private String commonCd;

  // KEY 값이 String 인 경우
  private String featureNo;

  // KEY 값이 int 인 경우
  private Long featureSeq;

}
