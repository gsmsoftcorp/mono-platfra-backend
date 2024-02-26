package com.gsm.platfra.api.data.account.otp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountOTPDto {
  private Long otpSeq;
  private String userId;
  private String otp;
  private Boolean active;
}
