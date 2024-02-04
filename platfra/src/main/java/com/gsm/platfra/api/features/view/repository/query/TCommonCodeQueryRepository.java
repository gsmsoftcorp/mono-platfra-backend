package com.gsm.platfra.api.features.view.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.gsm.platfra.api.data.common.code.QTCommonCode.tCommonCode;


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
