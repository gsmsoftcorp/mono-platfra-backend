package com.gsm.platfra.api.features.comment.dto;

import lombok.Getter;

@Getter
public class CommentListResDto {
    private Long featureCommentSeq;
    private String contentsCd;
    private Long contentsSeq;
    private String userId;
    private Long count;
}
