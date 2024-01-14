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

import com.gsm.platfra.api.services.platfra.dto.PlatfraResultDto;
import com.gsm.platfra.api.services.platfra.dto.PlatfraSearchDto;
import com.gsm.platfra.api.services.platfra.repository.TPlatfraRepository;
import com.gsm.platfra.api.services.platfra.repository.query.PlatfraQueryRepository;
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
public class PlatfraService {
    private final TPlatfraRepository tPlatfraRepository;
    private final PlatfraQueryRepository platfraQueryRepository;
    
    public List<PlatfraResultDto> getList(PlatfraSearchDto platfraSearchDto) {
        return platfraQueryRepository.getList(platfraSearchDto);
    }
}
