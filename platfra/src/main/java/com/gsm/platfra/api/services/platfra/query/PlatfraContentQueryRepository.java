package com.gsm.platfra.api.services.platfra.query;

import com.gsm.platfra.api.data.platfra.PlatfraContentDto;
import com.gsm.platfra.api.features.view.dto.FeatureViewCountDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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

    public List<PlatfraContentDto> getMyContents(String userId, Pageable pageable) {
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
                        tPlatfraContent.regUserId.eq(userId),
                        tPlatfraContent.delYn.eq(Boolean.FALSE)
                )
                .orderBy(tPlatfraContent.regDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
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

    public void updateCount(FeatureViewCountDto dto){
        queryFactory
                .update(tPlatfraContent)
                .set(tPlatfraContent.view, tPlatfraContent.view.add(dto.getCount()))
                .where(tPlatfraContent.contentSeq.eq(dto.getContentsSeq()))
                .execute();
    }
}























