package com.gsm.platfra.api.services.feature.view.repository.query;

import com.gsm.platfra.api.dto.common.CommonCodeDto;
import com.gsm.platfra.api.entity.common.QTCommonCode;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.gsm.platfra.api.entity.common.QTCommonCode.tCommonCode;

@Repository
@RequiredArgsConstructor
public class TCommonCodeQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<String> getContentsInfo() {
        return queryFactory
                .select(
                    tCommonCode.commonCd
                )
                .from(tCommonCode)
                .where(
                        tCommonCode.commonCd.contains("CONTENT")
                ).fetch();
    }
}
