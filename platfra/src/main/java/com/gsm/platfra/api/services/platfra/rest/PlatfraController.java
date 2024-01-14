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

import com.gsm.platfra.api.services.platfra.dto.PlatfraResultDto;
import com.gsm.platfra.api.services.platfra.dto.PlatfraSearchDto;
import com.gsm.platfra.api.services.platfra.service.PlatfraService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 플랫프라 마스터 컨트롤러
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/platfra")
public class PlatfraController {

    private final PlatfraService platfraService;
    
    /**
     * 플랫폼 마스터 검색 리스트 조회
     * @param platfraSearchDto
     * @return
     */
    @RequestMapping
    public List<PlatfraResultDto> getList(@RequestParam PlatfraSearchDto platfraSearchDto) {
        return platfraService.getList(platfraSearchDto);
    }
    



}

