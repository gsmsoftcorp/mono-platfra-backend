package com.gsm.platfra.api.services.account.query;

import com.gsm.platfra.api.data.account.otp.AccountOTPDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.gsm.platfra.api.data.account.otp.QTAccountOTP.tAccountOTP;
@Repository
@RequiredArgsConstructor
public class AccountOTPQueryRepository {
  private final JPAQueryFactory jpaQueryFactory;

  public AccountOTPDto findByUserIdLimit1(String userId){
    return jpaQueryFactory.select(
        Projections.fields(
            AccountOTPDto.class,
            tAccountOTP.otpSeq,
            tAccountOTP.userId,
            tAccountOTP.otp,
            tAccountOTP.active
        )
    ).from(tAccountOTP)
        .where(
            tAccountOTP.userId.eq(userId),
            tAccountOTP.active.eq(false))
        .orderBy(tAccountOTP.regDate.desc())
        .limit(1)
        .fetchOne();
  }


}
