package com.gsm.platfra.api.features.comment.service;

import com.gsm.platfra.api.data.feature.comment.FeatureCommentDto;
import com.gsm.platfra.api.data.feature.comment.TFeatureComment;
import com.gsm.platfra.api.features.comment.dto.CommentListResDto;
import com.gsm.platfra.api.data.feature.comment.TFeatureCommentRepository;
import com.gsm.platfra.api.features.comment.repository.query.FeatureCommentQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeatureCommentService {
    private final TFeatureCommentRepository tFeatureCommentRepository;
    private final FeatureCommentQueryRepository featureCommentQueryRepository;

    public void create(FeatureCommentDto featureCommentDto) {
        TFeatureComment tFeatureComment = FeatureCommentDto.toEntity(featureCommentDto);
        tFeatureCommentRepository.save(tFeatureComment);
    }

    public List<CommentListResDto> list(FeatureCommentDto featureCommentDto) {
        List<CommentListResDto> list = featureCommentQueryRepository.list(featureCommentDto);
        return list;
    }

    public List<FeatureCommentDto> reply(FeatureCommentDto featureCommentDto) {
        FeatureCommentDto commentDto = FeatureCommentDto.of(tFeatureCommentRepository.findById(featureCommentDto.getFeatureCommentSeq()).orElseThrow());
        return featureCommentQueryRepository.reply(commentDto);
    }
}
