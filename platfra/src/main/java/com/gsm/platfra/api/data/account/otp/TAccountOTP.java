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
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
@DynamicInsert
@Table(name = "T_ACCOUNT_OTP")
@ToString
public class TAccountOTP{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "OTP_SEQ", nullable = false)
  private Long otpSeq;

  @Column(name = "USER_ID", nullable = false, length = 64)
  private String userId;

  @Column(name = "EMAIL", nullable = false, length = 64)
  private String email;

  @Column(name = "OTP", nullable = false, length = 6)
  private String otp;

  @Column(name = "ACTIVE", nullable = false, length = 1)
  private Boolean active;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", insertable = false, updatable = false)
  private TAccount tAccount;

  @Column(name = "REG_DATE", nullable = false)
  @CreatedDate
  private LocalDateTime regDate;

  @Column(name = "MOD_DATE", nullable = false)
  @LastModifiedDate
  private LocalDateTime modDate;

  @Column(name = "REG_USER_ID", nullable = false)
  private String regUserId;

  @Column(name = "MOD_USER_ID", nullable = false)
  private String modUserId;
  public void update(){
    this.active = Boolean.TRUE;
  }
}
