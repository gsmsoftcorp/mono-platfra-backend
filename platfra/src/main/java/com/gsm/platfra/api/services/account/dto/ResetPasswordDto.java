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
public class ResetPasswordDto {

  @NotBlank(message = "아이디를 입력해 주세요.")
  private String userId;
  @NotBlank(message = "바꾸고 싶은 비밀번호를 입력해 주세요.")
  private String password;
  @NotBlank(message = "두 번호가 틀립니다.")
  private String checkPassword;

  public boolean samePwCheck(){
    return this.password.equals(checkPassword);
  }
}
