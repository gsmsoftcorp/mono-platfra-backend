package com.gsm.platfra.api.features.view.repository.query;

import com.gsm.platfra.api.data.feature.view.FeatureViewDto;
import com.gsm.platfra.api.features.view.dto.FeatureViewCountDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.gsm.platfra.api.data.feature.view.QTFeatureView.tFeatureView;


@Repository
@RequiredArgsConstructor
public class FeatureViewQueryRepository {
    private final JPAQueryFactory queryFactory;

    public FeatureViewDto isDuplicateAccess(FeatureViewDto dto) {
        return queryFactory
                .select(
                        Projections.fields(
                                FeatureViewDto.class,
                                tFeatureView.featureViewSeq,
                                tFeatureView.contentsCd,
                                tFeatureView.contentsSeq,
                                tFeatureView.userId
                        )
                )
                .from(tFeatureView)
                .where(
                        dto.getUserId().isBlank() ? null : tFeatureView.userId.eq(dto.getUserId()),
                        dto.getAddress().isBlank() ? null : tFeatureView.address.eq(dto.getAddress()),
                        dto.getContentsCd().isBlank() ? null : tFeatureView.contentsCd.eq(dto.getContentsCd()),
                        dto.getContentsSeq() == null ? null : tFeatureView.contentsSeq.eq(dto.getContentsSeq()),
                        tFeatureView.delYn.eq(Boolean.FALSE)
                ).fetchOne();
    }

    public List<FeatureViewCountDto> getAddViewList(String id) {
        return queryFactory
                .select(
                        Projections.fields(
                                FeatureViewCountDto.class,
                                tFeatureView.contentsCd,
                                tFeatureView.contentsSeq,
                                tFeatureView.count().as("count")
                        )
                )
                .from(tFeatureView)
                .where(
                        tFeatureView.contentsCd.eq(id),
//                        tFeatureView.regDate.between(LocalDateTime.now().minusHours(1), LocalDateTime.now())
                        tFeatureView.regDate.between(LocalDateTime.now().minusSeconds(10), LocalDateTime.now())
                )
                .groupBy(tFeatureView.contentsCd, tFeatureView.contentsSeq)
                .fetch();
    }
}
