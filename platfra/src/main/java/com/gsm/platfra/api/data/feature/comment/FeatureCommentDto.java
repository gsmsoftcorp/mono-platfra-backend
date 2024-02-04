package com.gsm.platfra.api.data.feature.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeatureCommentDto {
    private Long featureCommentSeq;
    private Long parentSeq;
    private String contentsCd;
    private Long contentsSeq;
    private String comment;
    private String userId;

    public static TFeatureComment toEntity(FeatureCommentDto dto) {
        return TFeatureComment.builder()
                .featureCommentSeq(dto.featureCommentSeq)
                .parentSeq(dto.parentSeq)
                .contentsCd(dto.contentsCd)
                .contentsSeq(dto.contentsSeq)
                .comment(dto.comment)
                .userId(dto.userId)
                .build();
    }

    public static FeatureCommentDto of(TFeatureComment tFeatureComment) {
        return FeatureCommentDto.builder()
                .featureCommentSeq(tFeatureComment.getFeatureCommentSeq())
                .parentSeq(tFeatureComment.getParentSeq())
                .contentsCd(tFeatureComment.getContentsCd())
                .contentsSeq(tFeatureComment.getContentsSeq())
                .comment(tFeatureComment.getComment())
                .userId(tFeatureComment.getUserId())
                .build();
    }

}
