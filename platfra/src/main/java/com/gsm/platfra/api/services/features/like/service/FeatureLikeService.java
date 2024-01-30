package com.gsm.platfra.api.services.features.like.service;

import com.gsm.platfra.api.dto.feat.like.FeatureLikeDto;
import com.gsm.platfra.api.entity.feature.TFeatureLike;
import com.gsm.platfra.api.services.features.like.mapper.FeatureLikeMapper;
import com.gsm.platfra.api.services.features.like.repository.TFeatureLikeRepository;
import com.gsm.platfra.api.services.features.like.repository.query.TFeatureLikeQueryRepository;
import com.gsm.platfra.system.security.context.UserContextUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FeatureLikeService {
    private final TFeatureLikeRepository likeRepository;
    private final TFeatureLikeQueryRepository likeQueryRepository;
    private final FeatureLikeMapper likeMapper;

    @Transactional
    public void like(FeatureLikeDto dto){
        dto.setUserId(UserContextUtil.getUserContext().getUserId());
        System.out.println(dto);
        TFeatureLike like = likeRepository.save(likeMapper.dtoToEntity(dto));
        like.update();
        likeRepository.flush();
    }
}
