package com.gsm.platfra.api.services.features.like.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TFeatureLikeQueryRepository {
    private final JPAQueryFactory queryFactory;
}
