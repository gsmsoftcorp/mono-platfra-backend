package com.gsm.platfra.api.services.account.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckOTPDto {

  @NotBlank(message="아이디를 입력해주세요.")
  private String userId;

  @NotBlank(message="OTP 번호를 입력하세요.")
  private String otp;

}
