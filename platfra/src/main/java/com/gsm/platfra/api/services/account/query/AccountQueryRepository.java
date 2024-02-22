package com.gsm.platfra.api.services.account.query;

import com.gsm.platfra.api.data.account.AccountDto;
import com.gsm.platfra.api.data.account.QTAccount;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.gsm.platfra.api.data.account.QTAccount.tAccount;

@Repository
@RequiredArgsConstructor
public class AccountQueryRepository {
    private final JPAQueryFactory queryFactory;

    public void updateInfo(AccountDto dto) {
        queryFactory
                .update(tAccount)
                .set(tAccount.age, dto.getAge())
                .set(tAccount.birthday, dto.getBirthday())
                .set(tAccount.gender, dto.getGender())
                .set(tAccount.phone, dto.getPhone())
                .set(tAccount.userNm, dto.getUserNm())
                .set(tAccount.nickname, dto.getNickname())
                .where(tAccount.userId.eq(dto.getUserId()))
                .execute();
    }

    public AccountDto findById(String userId){
        return queryFactory
                .select(
                        Projections.fields(
                                AccountDto.class,
                                tAccount.userId,
                                tAccount.email,
                                tAccount.phone,
                                tAccount.userNm,
                                tAccount.nickname,
                                tAccount.age,
                                tAccount.gender,
                                tAccount.birthday
                        )
                )
                .from(tAccount)
                .where(
                        tAccount.userId.eq(userId),
                        tAccount.delYn.eq(Boolean.FALSE)
                )
                .fetchOne();
    }
}
