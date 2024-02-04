package com.gsm.platfra.api.services.features.like.rest;

import com.gsm.platfra.api.dto.feat.like.FeatureLikeDto;
import com.gsm.platfra.api.services.features.like.dto.FeatureLikeResDto;
import com.gsm.platfra.api.services.features.like.service.FeatureLikeService;
import com.gsm.platfra.system.security.context.UserContextUtil;
import java.util.List;
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
    public FeatureLikeResDto getLikeList(FeatureLikeDto dto){
        return featureLikeService.getContentLikeList(dto);
    }

    /**
    * 좋아요 등록 및 취소
    * @param featureLikeDto
    * @return
    * */
    @PutMapping
    public void like(@RequestBody FeatureLikeDto featureLikeDto){
        featureLikeService.like(featureLikeDto);
    }
}
