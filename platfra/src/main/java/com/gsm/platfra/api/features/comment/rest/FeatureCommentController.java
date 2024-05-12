package com.gsm.platfra.api.features.comment.rest;

import com.gsm.platfra.api.data.base.BaseResponse;
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


    /**
     * 댓글 생성
     * parentSeq와 함께 요청 시 대댓글 생성
     * @param featureCommentDto
     */
    @PostMapping
    public BaseResponse create(@RequestBody FeatureCommentDto featureCommentDto) {
        featureCommentService.create(featureCommentDto);
        return BaseResponse.builder()
                .data(null)
                .code(null)
                .message(null)
                .error(null)
                .build();
    }

    /**
     * 댓글 조회
     * 대댓글은 count로 응답
     * @param featureCommentDto
     * @return
     */
    @GetMapping
    public BaseResponse list(FeatureCommentDto featureCommentDto) {
        return BaseResponse.builder()
                .data(featureCommentService.list(featureCommentDto))
                .code(null)
                .message(null)
                .error(null)
                .build();
    }

    /**
     * 대댓글 조회
     * @param featureCommentDto
     * @return
     */
    @GetMapping("/reply")
    public BaseResponse reply(FeatureCommentDto featureCommentDto) {
        return BaseResponse.builder()
                .data(featureCommentService.reply(featureCommentDto))
                .code(null)
                .message(null)
                .error(null)
                .build();
    }

    /**
     * 댓글 수정
     * @param featureCommentDto
     */
    @PutMapping
    public BaseResponse update(@RequestBody FeatureCommentDto featureCommentDto) {
        featureCommentService.update(featureCommentDto);
        return BaseResponse.builder()
                .data(null)
                .code(null)
                .message(null)
                .error(null)
                .build();
    }

    /**
     * 댓글 삭제
     * @param featureCommentDto
     */
    @DeleteMapping
    public BaseResponse delete(@RequestBody FeatureCommentDto featureCommentDto) {
        featureCommentService.delete(featureCommentDto);
        return BaseResponse.builder()
                .data(null)
                .code(null)
                .message(null)
                .error(null)
                .build();
    }
}
