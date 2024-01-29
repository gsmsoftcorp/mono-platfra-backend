package com.gsm.platfra.api.services.feature.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class TFeatureViewQueryRepository {
    private final JPAQueryFactory queryFactory;

}