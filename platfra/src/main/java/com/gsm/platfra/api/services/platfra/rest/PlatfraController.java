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

import com.gsm.platfra.api.services.platfra.dto.PlatfraMainResDto;
import com.gsm.platfra.api.dto.platfra.PlatfraDto;
import com.gsm.platfra.api.services.platfra.service.PlatfraService;
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
@RequestMapping("/platfra")
public class PlatfraController {
    
    private final PlatfraService platfraService;

    /**
     * 플랫폼 리스트 조회
     * @param platfraDto
     * @return
     */
    @GetMapping
    public List<PlatfraDto> getList(PlatfraDto platfraDto) {
        return platfraService.getList(platfraDto);
    }
    
    /**
     * 플랫폼 메인(상세) 조회
     * @param platfraSeq
     * @return
     */
    @GetMapping("/{platfraSeq}")
    public PlatfraMainResDto get(@PathVariable(required = true) Long platfraSeq) {
        return platfraService.get(platfraSeq);
    }
    
    /**
     * 플랫폼 등록
     * @param platfraDto
     */
    @PostMapping
    public void create(@RequestBody PlatfraDto platfraDto) {
        platfraService.create(platfraDto);
    }
    
    /**
     * 플랫폼 수정
     * @param platfraDto
     */
    @PutMapping
    public void update(@RequestBody PlatfraDto platfraDto) {
        platfraService.update(platfraDto);
    }
    
    /**
     * 플랫폼 삭제
     * @param platfraDto
     */
    @DeleteMapping
    public void delete(@RequestBody PlatfraDto platfraDto) {
        platfraService.delete(platfraDto);
    }
}

