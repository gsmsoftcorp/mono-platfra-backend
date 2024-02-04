package com.gsm.platfra.api.features.comment.rest;

import com.gsm.platfra.api.data.feature.comment.FeatureCommentDto;
import com.gsm.platfra.api.features.comment.dto.CommentListResDto;
import com.gsm.platfra.api.features.comment.service.FeatureCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class FeatureCommentController {
    private final FeatureCommentService featureCommentService;


    @PostMapping
    public void create(@RequestBody FeatureCommentDto featureCommentDto) {
        featureCommentService.create(featureCommentDto);
    }

    @GetMapping
    public List<CommentListResDto> list(@RequestBody FeatureCommentDto featureCommentDto) {
        return featureCommentService.list(featureCommentDto);
    }

    @GetMapping("/reply")
    public List<FeatureCommentDto> reply(@RequestBody FeatureCommentDto featureCommentDto) {
        return featureCommentService.reply(featureCommentDto);
    }
}
