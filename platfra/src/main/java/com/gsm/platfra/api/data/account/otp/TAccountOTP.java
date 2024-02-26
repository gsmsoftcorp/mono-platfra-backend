package com.gsm.platfra.api.data.account.otp;

import com.gsm.platfra.api.data.account.TAccount;
import com.gsm.platfra.api.data.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
@Table(name = "T_ACCOUNT_OTP")
public class TAccountOTP extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "OTP_SEQ", nullable = false)
  private Long otpSeq;

  @NotNull
  @Column(name = "USER_ID", nullable = false, length = 64)
  private String userId;

  @NotNull
  @Column(name = "OTP", nullable = false, length = 6)
  private String otp;

  @NotNull
  @Column(name = "ACTIVE", nullable = false, length = 1)
  private Boolean active;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", insertable = false, updatable = false)
  private TAccount tAccount;

  public void update(){
    this.active = Boolean.TRUE;
  }
}
