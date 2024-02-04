package com.gsm.platfra.api.services.features.like.service;

import com.gsm.platfra.api.dto.feat.like.FeatureLikeDto;
import com.gsm.platfra.api.entity.feature.TFeatureLike;
import com.gsm.platfra.api.services.features.like.dto.FeatureLikeResDto;
import com.gsm.platfra.api.services.features.like.mapper.FeatureLikeMapper;
import com.gsm.platfra.api.services.features.like.repository.TFeatureLikeRepository;
import com.gsm.platfra.api.services.features.like.repository.query.TFeatureLikeQueryRepository;
import com.gsm.platfra.system.security.context.UserContextUtil;
import java.util.List;
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
        TFeatureLike like = likeQueryRepository.getLike(dto);

        if(like == null){
            dto.setLikeYn(Boolean.TRUE);
            likeRepository.saveAndFlush(likeMapper.dtoToEntity(dto));
        }
        else{
            like.update();
            System.out.println(like);
            likeRepository.saveAndFlush(like);
        }
    }

    public FeatureLikeResDto getContentLikeList(FeatureLikeDto dto){
        
        List<FeatureLikeDto> likeList = likeQueryRepository.getLikeList(dto);
        return FeatureLikeResDto.builder()
            .featureLikeList(likeList)
            .contentLikeCount(likeList.size())
            .build();
    }
}
