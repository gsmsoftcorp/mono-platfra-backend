package com.gsm.platfra.api.features.like.rest;

import com.gsm.platfra.api.data.base.BaseResponse;
import com.gsm.platfra.api.data.feature.like.FeatureLikeDto;
import com.gsm.platfra.api.features.like.dto.FeatureLikeResDto;
import com.gsm.platfra.api.features.like.service.FeatureLikeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/feature/like")
public class FeatureLikeController {
    private final FeatureLikeService featureLikeService;

    /**
    * 좋아요 조회
    * @param
    * @return
    * */
    @GetMapping
    public BaseResponse getLikeList(@Valid FeatureLikeDto dto){
        return BaseResponse.builder()
                .data(featureLikeService.getContentLikeList(dto))
                .code(null)
                .message(null)
                .error(null)
                .build();
    }

    /**
    * 좋아요 등록 및 취소
    * @param featureLikeDto
    * @return
    * */
    @PutMapping
    public BaseResponse like(@RequestBody @Valid FeatureLikeDto featureLikeDto){
        featureLikeService.like(featureLikeDto);
        return BaseResponse.builder()
                .data(null)
                .code(null)
                .message(null)
                .error(null)
                .build();
    }
}
