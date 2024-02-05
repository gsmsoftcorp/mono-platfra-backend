package com.gsm.platfra.api.features.like.query;


import com.gsm.platfra.api.data.feature.like.FeatureLikeDto;
import com.gsm.platfra.api.data.feature.like.TFeatureLike;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.gsm.platfra.api.data.feature.like.QTFeatureLike.tFeatureLike;

@Repository
@RequiredArgsConstructor
public class TFeatureLikeQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<FeatureLikeDto> getLikeList(FeatureLikeDto dto){
        return
            queryFactory
                .select(
                    Projections.fields(
                        FeatureLikeDto.class,
                        tFeatureLike.featureLikeSeq,
                        tFeatureLike.contentsSeq,
                        tFeatureLike.contentsCd,
                        tFeatureLike.userId,
                        tFeatureLike.regUserId,
                        tFeatureLike.modUserId,
                        tFeatureLike.likeYn
                    )
                )
                .from(tFeatureLike)
                .where(
                    tFeatureLike.contentsCd.eq(dto.getContentsCd()),
                    tFeatureLike.contentsSeq.eq(dto.getContentsSeq())
                ).fetch();
    }

    public TFeatureLike getLike(FeatureLikeDto dto){
        return queryFactory
            .select(
                Projections.fields(
                    TFeatureLike.class, //TODO DTO로 조회 바랍니다
                tFeatureLike.featureLikeSeq,
                tFeatureLike.contentsCd,
                tFeatureLike.contentsSeq,
                tFeatureLike.likeYn,
                tFeatureLike.userId,
                tFeatureLike.regUserId,
                tFeatureLike.modUserId,
                tFeatureLike.regDate,
                tFeatureLike.modDate
            )
        )
            .from(tFeatureLike)
            .where(
                tFeatureLike.userId.eq(dto.getUserId()),
                tFeatureLike.contentsCd.eq(dto.getContentsCd()),
                tFeatureLike.contentsSeq.eq(dto.getContentsSeq())
            ).fetchOne();
    }
}
