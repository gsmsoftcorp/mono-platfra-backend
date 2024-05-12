package com.gsm.platfra.api.services.platfra.service;

import com.gsm.platfra.api.data.platfra.PlatfraContentDto;
import com.gsm.platfra.api.data.platfra.TPlatfraContent;
import com.gsm.platfra.api.services.platfra.dto.PlatfraContentMainResDto;
import com.gsm.platfra.api.data.platfra.TPlatfraContentRepository;
import com.gsm.platfra.api.services.platfra.query.PlatfraContentQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlatfraContentService {
    private final TPlatfraContentRepository tPlatfraContentRepository;
    private final PlatfraContentQueryRepository platfraContentQueryRepository;
    
    public List<PlatfraContentDto> getList(String platfraId) {
        List<PlatfraContentDto> platfraContentDtoList = platfraContentQueryRepository.getList(platfraId);
        
        return platfraContentDtoList;
    }
    public PlatfraContentDto get(Long contentSeq) {
        //1. 컨텐츠 조회
        TPlatfraContent tPlatfraContent = tPlatfraContentRepository.findById(contentSeq).orElseThrow(/*DataNotFoundException::new*/);
        PlatfraContentDto platfraContentDto = PlatfraContentDto.of(tPlatfraContent);

        return platfraContentDto;
    }
    
    @Transactional
    public void create(PlatfraContentDto platfraContentDto) {
        TPlatfraContent tPlatfraContent = TPlatfraContent.builder()
            .platfraId(platfraContentDto.getPlatfraId())
            .title(platfraContentDto.getTitle())
            .content(platfraContentDto.getContent())
            .build();
        
        tPlatfraContentRepository.saveAndFlush(tPlatfraContent);
    }
    
    @Transactional
    public void update(PlatfraContentDto platfraContentDto) {
        TPlatfraContent tPlatfraContent = tPlatfraContentRepository.findById(platfraContentDto.getContentSeq()).orElseThrow(/*DataNotFoundException::new*/);
        tPlatfraContent.update(platfraContentDto);
        
        tPlatfraContentRepository.flush();
    }
    
    @Transactional
    public void delete(PlatfraContentDto platfraContentDto) {
        platfraContentQueryRepository.delete(platfraContentDto);
    }
    
}
