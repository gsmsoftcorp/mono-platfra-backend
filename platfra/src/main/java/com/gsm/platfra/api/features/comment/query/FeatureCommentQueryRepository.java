package com.gsm.platfra.api.features.comment.query;

import com.gsm.platfra.api.data.feature.comment.FeatureCommentDto;
import com.gsm.platfra.api.data.feature.comment.QTFeatureComment;
import com.gsm.platfra.api.features.comment.dto.CommentListResDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.gsm.platfra.api.data.feature.comment.QTFeatureComment.tFeatureComment;


@Repository
@RequiredArgsConstructor
public class FeatureCommentQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<CommentListResDto> list(FeatureCommentDto dto) {
        QTFeatureComment reply = new QTFeatureComment("reply");
        return queryFactory
                .select(
                        Projections.fields(
                                CommentListResDto.class,
                                tFeatureComment.featureCommentSeq,
                                tFeatureComment.contentsCd,
                                tFeatureComment.contentsSeq,
                                tFeatureComment.comment,
                                tFeatureComment.userId,
                                reply.count().as("count")
//                                JPAExpressions
//                                        .select(reply.count()) // 대댓글 개수 계산
//                                        .from(reply) // 대댓글을 위한 별칭 사용
//                                        .where(reply.parentSeq.eq(tFeatureComment.featureCommentSeq)) // 올바른 조건 지정
                        )
                )
                .from(tFeatureComment)
                .leftJoin(reply).on(reply.parentSeq.eq(tFeatureComment.featureCommentSeq))
                .where(
                        tFeatureComment.contentsCd.eq(dto.getContentsCd()),
                        tFeatureComment.contentsSeq.eq(dto.getContentsSeq()),
                        tFeatureComment.parentSeq.isNull(),
                        tFeatureComment.delYn.eq(Boolean.FALSE)
                )
                .groupBy(tFeatureComment.featureCommentSeq)
                .orderBy(tFeatureComment.regDate.desc())
                .fetch();
    }

    public List<FeatureCommentDto> reply(FeatureCommentDto dto) {
        return queryFactory
                .select(
                        Projections.fields(
                                FeatureCommentDto.class,
                                tFeatureComment.featureCommentSeq,
                                tFeatureComment.parentSeq,
                                tFeatureComment.contentsCd,
                                tFeatureComment.contentsSeq,
                                tFeatureComment.comment,
                                tFeatureComment.userId
                        )
                )
                .from(tFeatureComment)
                .where(
                        tFeatureComment.contentsCd.eq(dto.getContentsCd()),
                        tFeatureComment.contentsSeq.eq(dto.getContentsSeq()),
                        tFeatureComment.parentSeq.eq(dto.getFeatureCommentSeq()),
                        tFeatureComment.delYn.eq(Boolean.FALSE)
                )
                .orderBy(tFeatureComment.regDate.desc())
                .fetch();
    }

    public void delete(FeatureCommentDto featureCommentDto) {
        queryFactory
                .update(tFeatureComment)
                .set(tFeatureComment.delYn, Boolean.TRUE)
                .where(
                        tFeatureComment.featureCommentSeq.eq(featureCommentDto.getFeatureCommentSeq()),
                        tFeatureComment.contentsCd.eq(featureCommentDto.getContentsCd()),
                        tFeatureComment.contentsSeq.eq(featureCommentDto.getContentsSeq())
                )
                .execute()
        ;
    }
}
