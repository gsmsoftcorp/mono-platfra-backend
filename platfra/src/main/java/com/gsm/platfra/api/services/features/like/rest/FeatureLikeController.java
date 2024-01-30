package com.gsm.platfra.api.services.features.like.rest;

import com.gsm.platfra.api.dto.feat.like.FeatureLikeDto;
import com.gsm.platfra.api.services.features.like.service.FeatureLikeService;
import com.gsm.platfra.system.security.context.UserContextUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    @PutMapping
    public void like(@RequestBody FeatureLikeDto featureLikeDto) throws Exception{
        featureLikeService.like(featureLikeDto);
    }
}
