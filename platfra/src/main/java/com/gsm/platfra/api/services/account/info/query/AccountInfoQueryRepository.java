package com.gsm.platfra.api.services.account.info.query;

import com.gsm.platfra.api.data.account.info.AccountInfoDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.gsm.platfra.api.data.account.info.QTAccountInfo.tAccountInfo;
import static com.gsm.platfra.api.data.platfra.QTPlatfra.tPlatfra;


@Repository
@RequiredArgsConstructor
public class AccountInfoQueryRepository {
    private final JPAQueryFactory queryFactory;

    public AccountInfoDto get(String userId, String type) {
        return queryFactory
                .select(
                    Projections.fields(
                            AccountInfoDto.class,
                            tAccountInfo.accountInfoSeq,
                            tAccountInfo.tAccount,
                            tAccountInfo.tCommonFile,
                            tAccountInfo.message,
                            tAccountInfo.type
                    ))
                .from(tAccountInfo)
                .where(
                        tAccountInfo.tAccount.userId.eq(userId),
                        tAccountInfo.type.eq(type)
                )
                .fetchOne();
    }

    public void update(AccountInfoDto accountInfoDto) {
        JPAUpdateClause updateClause = queryFactory
                .update(tAccountInfo)
                .where(tAccountInfo.tAccount.userId.eq(accountInfoDto.getUserId()));
        if (accountInfoDto.getMessage() != null) {
            updateClause.set(tAccountInfo.message, accountInfoDto.getMessage());
        }
        if (accountInfoDto.getTAccount() != null) {
            updateClause.set(tAccountInfo.tAccount, accountInfoDto.getTAccount());
        }
        if (accountInfoDto.getTCommonFile() != null) {
            updateClause.set(tAccountInfo.tCommonFile, accountInfoDto.getTCommonFile());
        }
        updateClause.execute();
    }
}
