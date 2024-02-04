package com.gsm.platfra.api.services.features.like.repository.query;

import static com.gsm.platfra.api.data.feature.like.QTFeatureLike.tFeatureLike;

import com.gsm.platfra.api.data.feature.like.TFeatureLike;
import com.gsm.platfra.api.dto.feat.like.FeatureLikeDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
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
                    TFeatureLike.class,
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
