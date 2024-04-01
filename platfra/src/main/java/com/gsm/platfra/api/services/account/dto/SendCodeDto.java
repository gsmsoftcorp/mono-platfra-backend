package com.gsm.platfra.api.services.account.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SendCodeDto {

  @NotBlank(message = "이메일을 입력해 주세요.")
  @Email(message = "이메일 형식이 올바르지 않습니다.")
  private String email;

  @NotBlank(message = "코드를 입력해 주세요.")
  private String otp;

  @NotBlank(message = "아이디를 입력해 주세요.")
  private String userId;
}
