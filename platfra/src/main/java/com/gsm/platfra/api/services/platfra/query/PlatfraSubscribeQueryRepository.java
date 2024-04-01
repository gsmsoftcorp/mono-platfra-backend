package com.gsm.platfra.api.services.platfra.query;

import com.gsm.platfra.api.data.platfra.PlatfraDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.gsm.platfra.api.data.platfra.QTPlatfra.tPlatfra;
import static com.gsm.platfra.api.data.platfra.category.QTPlatfraCategory.tPlatfraCategory;
import static com.gsm.platfra.api.data.platfra.subscribe.QTPlatfraSubscribe.tPlatfraSubscribe;
import static com.querydsl.jpa.JPAExpressions.select;

@Repository
@RequiredArgsConstructor
public class PlatfraSubscribeQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<PlatfraDto> getSubscribedPlatfra(String userId) {

        return queryFactory
                .select(
                        Projections.fields(
                                PlatfraDto.class,
                                tPlatfra.platfraSeq,
                                tPlatfra.platfraId,
                                tPlatfra.subject,
                                tPlatfra.description,
                                tPlatfra.introduction,
                                tPlatfraCategory.id.categoryCd
                        )
                )
                .from(tPlatfra)
                .where(tPlatfra.platfraId.in(
                        select(tPlatfraSubscribe.platfra.platfraId)
                                .from(tPlatfraSubscribe)
                                .where(tPlatfraSubscribe.userId.eq(userId)))
                )
                .fetch();
    }
}
