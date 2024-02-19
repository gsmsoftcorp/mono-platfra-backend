package com.gsm.platfra.api.features.like.service;

import com.gsm.platfra.api.common.code.service.CommonCodeService;
import com.gsm.platfra.api.data.feature.like.FeatureLikeDto;
import com.gsm.platfra.api.data.feature.like.TFeatureLike;
import com.gsm.platfra.api.data.feature.like.TFeatureLikeRepository;
import com.gsm.platfra.api.features.dto.FeatureDto;
import com.gsm.platfra.api.features.like.dto.FeatureLikeResDto;
import com.gsm.platfra.api.features.like.mapper.FeatureLikeMapper;
import com.gsm.platfra.api.features.like.query.TFeatureLikeQueryRepository;
import com.gsm.platfra.system.security.context.UserContextUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FeatureLikeService {
    private final TFeatureLikeRepository likeRepository;
    private final TFeatureLikeQueryRepository likeQueryRepository;
    private final FeatureLikeMapper likeMapper;
    private final CommonCodeService commonCodeService;
    @Transactional
    public void like(FeatureLikeDto dto){

        FeatureDto featureDto = FeatureDto
            .builder()
            .commonCd(dto.getContentsCd())
            .featureSeq(dto.getContentsSeq())
            .build();

        if(!commonCodeService.checkUsableFeat(featureDto)){
            throw new EntityNotFoundException("존재하지 않는 "+featureDto.getCommonCd()+" 입니다.");
        }

        TFeatureLike like = FeatureLikeDto.toEntity(likeQueryRepository.getLike(dto));
        System.out.println(like);

        if(like == null){
            dto.setLikeYn(Boolean.TRUE);
            likeRepository.saveAndFlush(likeMapper.dtoToEntity(dto));
        } else{
            like.update();
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
