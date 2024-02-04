package com.gsm.platfra.api.services.platfra.repository.query;

import com.gsm.platfra.api.data.platfra.PlatfraContentDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.gsm.platfra.api.data.platfra.QTPlatfraContent.tPlatfraContent;


/**
 *
 */
@Repository
@RequiredArgsConstructor
public class PlatfraContentQueryRepository {
    private final JPAQueryFactory queryFactory;
    
    public List<PlatfraContentDto> getList(String platfraId) {
        List<PlatfraContentDto> list = queryFactory
            .select(
                Projections.fields(
                    PlatfraContentDto.class,
                    tPlatfraContent.contentSeq,
                    tPlatfraContent.platfraId,
                    tPlatfraContent.title,
                    tPlatfraContent.content
                )
            )
            .from(tPlatfraContent)
            .where(
                tPlatfraContent.platfraId.eq(platfraId),
                tPlatfraContent.delYn.eq(Boolean.FALSE)
            )
            .orderBy(tPlatfraContent.regDate.desc())
            .fetch()
            ;
        
        return list;
        
    }
    
    public void delete(PlatfraContentDto platfraContentDto) {
        queryFactory
            .update(tPlatfraContent)
            .set(tPlatfraContent.delYn, Boolean.TRUE)
            .where(
                tPlatfraContent.contentSeq.eq(platfraContentDto.getContentSeq()),
                tPlatfraContent.platfraId.eq(platfraContentDto.getPlatfraId()),
                tPlatfraContent.contentNo.eq(platfraContentDto.getContentNo())
            )
            .execute()
        ;
    }
}























