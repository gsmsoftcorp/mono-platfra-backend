package com.gsm.platfra.api.services.platfra.repository.query;

import com.gsm.platfra.api.data.platfra.PlatfraContentDto;
import com.gsm.platfra.api.data.platfra.PlatfraDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.gsm.platfra.api.data.feature.like.QTFeatureLike.tFeatureLike;
import static com.gsm.platfra.api.data.feature.view.QTFeatureView.tFeatureView;
import static com.gsm.platfra.api.data.platfra.QTPlatfra.tPlatfra;
import static com.gsm.platfra.api.data.platfra.QTPlatfraContent.tPlatfraContent;
import static com.gsm.platfra.api.data.platfra.category.QTPlatfraCategory.tPlatfraCategory;


/**
 *
 */
@Repository
@RequiredArgsConstructor
public class PlatfraQueryRepository {
    private final JPAQueryFactory queryFactory;
    
    public List<PlatfraDto> getList(PlatfraDto platfraDto) {
        List<PlatfraDto> list = queryFactory
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
            .leftJoin(tPlatfraCategory)
            .on(tPlatfra.platfraId.eq(tPlatfraCategory.id.platfraId))
            .where(
                platfraDto.getPlatfraId().isBlank() ? null : tPlatfra.platfraId.eq(platfraDto.getPlatfraId()),
                platfraDto.getSubject().isBlank() ? null : tPlatfra.subject.contains(platfraDto.getSubject()),
                platfraDto.getDescription().isBlank() ? null : tPlatfra.description.contains(platfraDto.getDescription()),
                platfraDto.getIntroduction().isBlank() ? null : tPlatfra.introduction.contains(platfraDto.getIntroduction()),
                tPlatfra.delYn.eq(Boolean.FALSE)
            )
            .fetch()
            ;
        
        return list;
    }
    public List<PlatfraContentDto> getLatestContentList(String platfraId) {
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
    
    public List<PlatfraContentDto> getHotContentList(String platfraId) {
        NumberPath<Long> likeCount = Expressions.numberPath(Long.class, "likeCount");
        NumberPath<Long> viewCount = Expressions.numberPath(Long.class, "viewCount");
        List<PlatfraContentDto> list = queryFactory
            .select(
                Projections.fields(
                    PlatfraContentDto.class,
                    tPlatfraContent.contentSeq,
                    tPlatfraContent.platfraId,
                    tPlatfraContent.title,
                    tPlatfraContent.content,
                    tFeatureLike.featureLikeSeq.count().as(likeCount),
                    tFeatureView.featureViewSeq.count().as(viewCount)
                )
            )
            .from(tPlatfraContent)
            .leftJoin(tFeatureLike)
            .on(
                tFeatureLike.contentsSeq.eq(tPlatfraContent.contentSeq),
                tFeatureLike.contentsCd.eq("PLATFRA"),
                tFeatureLike.likeYn.eq(Boolean.TRUE)
            )
            .leftJoin(tFeatureView)
            .on(
                tFeatureView.contentsSeq.eq(tPlatfraContent.contentSeq),
                tFeatureView.contentsCd.eq("PLATFRA")
            )
            .where(
                tPlatfraContent.platfraId.eq(platfraId),
                tPlatfraContent.delYn.eq(Boolean.FALSE)
            )
            .groupBy(
                tPlatfraContent.contentSeq,
                tPlatfraContent.platfraId,
                tPlatfraContent.title,
                tPlatfraContent.content
            )
            .orderBy(
                likeCount.desc(),
                viewCount.desc()
            )
            .fetch()
            ;
        
        return list;
    }
    
    public void delete(Long platfraSeq) {
        queryFactory
            .update(tPlatfra)
            .set(tPlatfra.delYn, Boolean.TRUE)
            .where(
                tPlatfra.platfraSeq.eq(platfraSeq)
            )
            .execute()
        ;
    }
    
}























