package com.gsm.platfra.api.services.account.query;

import com.gsm.platfra.api.data.account.otp.AccountOTPDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

  /* 5분 이내에 OTP 인증이 있는지 확인 */
  public boolean checkOTP(String userId){
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime limitDate = now.minusMinutes(5);

    return jpaQueryFactory
        .selectOne()
        .from(tAccountOTP)
        .where(
            tAccountOTP.active.eq(Boolean.TRUE),
            tAccountOTP.userId.eq(userId)
        ).orderBy(tAccountOTP.regDate.desc()).fetchFirst() != null;
  }


}
