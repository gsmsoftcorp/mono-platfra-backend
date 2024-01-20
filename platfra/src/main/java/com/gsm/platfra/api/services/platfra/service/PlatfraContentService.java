/*
 * Copyright (C) Hanwha Systems Ltd., 2021. All rights reserved.
 *
 * This software is covered by the license agreement between
 * the end user and Hanwha Systems Ltd., and may be
 * used and copied only in accordance with the terms of the
 * said agreement.
 *
 * Hanwha Systems Ltd., assumes no responsibility or
 * liability for any errors or inaccuracies in this software,
 * or any consequential, incidental or indirect damage arising
 * out of the use of the software.
 */

package com.gsm.platfra.api.services.platfra.service;

import com.gsm.platfra.api.entity.platfra.TPlatfraContent;
import com.gsm.platfra.api.services.platfra.dto.PlatfraContentMainResDto;
import com.gsm.platfra.api.services.platfra.dto.table.PlatfraContentDto;
import com.gsm.platfra.api.services.platfra.repository.TPlatfraContentRepository;
import com.gsm.platfra.api.services.platfra.repository.query.PlatfraContentQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class PlatfraContentService {
    private final TPlatfraContentRepository tPlatfraContentRepository;
    private final PlatfraContentQueryRepository platfraContentQueryRepository;
    
    public List<PlatfraContentDto> getList(String platfraId) {
        List<PlatfraContentDto> platfraContentDtoList = platfraContentQueryRepository.getList(platfraId);
        
        return platfraContentDtoList;
    }
    public PlatfraContentMainResDto get(Long contentSeq) {
        // note : 여러개의 데이터를 조합하여 응답할때는 [테이블명]DTO 즉, 테이블용 DTO로 조회하여 service 단에서 화면출력용 DTO에 세팅한다.
        PlatfraContentMainResDto platfraContentMainResDto = new PlatfraContentMainResDto();
        
        //1. 컨텐츠 조회
        TPlatfraContent tPlatfraContent = tPlatfraContentRepository.findById(contentSeq).orElseThrow(/*DataNotFoundException::new*/);
        PlatfraContentDto platfraContentDto = PlatfraContentDto.of(tPlatfraContent);
        platfraContentMainResDto.setPlatfraContenDto(platfraContentDto);
        
        return platfraContentMainResDto;
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
