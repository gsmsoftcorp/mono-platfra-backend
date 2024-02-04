package com.gsm.platfra.api.features.view.repository.query;

import com.gsm.platfra.api.features.view.dto.FeatureViewCountDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.gsm.platfra.api.data.platfraboard.QTPlatfraBoardContent.tPlatfraBoardContent;


@Repository
@RequiredArgsConstructor
public class TPlatfraBoardContentQueryRepository {
    private final JPAQueryFactory queryFactory;

    public void updateCount(FeatureViewCountDto dto){
        queryFactory
                .update(tPlatfraBoardContent)
                .set(tPlatfraBoardContent.view, tPlatfraBoardContent.view.add(dto.getCount()))
                .where(tPlatfraBoardContent.contentSeq.eq(dto.getContentsSeq()))
                .execute();
    }
}