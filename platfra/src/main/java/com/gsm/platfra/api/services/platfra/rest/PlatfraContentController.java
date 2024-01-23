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
package com.gsm.platfra.api.services.platfra.rest;

import com.gsm.platfra.api.services.platfra.dto.PlatfraContentMainResDto;
import com.gsm.platfra.api.dto.platfra.PlatfraContentDto;
import com.gsm.platfra.api.services.platfra.service.PlatfraContentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 플랫프라 마스터 컨트롤러
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/platfra/content")
public class PlatfraContentController {

    private final PlatfraContentService platfraContentService;
    
    /**
     * 컨텐츠 리스트 조회
     * @param platfraId
     * @return
     */
    @GetMapping
    public List<PlatfraContentDto> getList(@RequestParam(required = true) String platfraId) {
        return platfraContentService.getList(platfraId);
    }
    
    /**
     * 컨텐츠 메인(상세) 조회
     * @param contentSeq
     * @return
     */
    @GetMapping("/{contentSeq}")
    public PlatfraContentMainResDto get(@PathVariable(required = true) Long contentSeq) {
        return platfraContentService.get(contentSeq);
    }
    
    /**
     * 컨텐츠 등록
     * @param platfraContentDto
     */
    @PostMapping
    public void create(@RequestBody PlatfraContentDto platfraContentDto) {
        platfraContentService.create(platfraContentDto);
    }
    
    /**
     * 컨텐츠 수정
     * @param platfraContentDto
     */
    @PutMapping
    public void update(@RequestBody PlatfraContentDto platfraContentDto) {
        platfraContentService.update(platfraContentDto);
    }
    
    /**
     * 컨텐츠 삭제
     * @param platfraContentDto
     */
    @DeleteMapping
    public void delete(@RequestBody PlatfraContentDto platfraContentDto) {
        platfraContentService.delete(platfraContentDto);
    }
}

