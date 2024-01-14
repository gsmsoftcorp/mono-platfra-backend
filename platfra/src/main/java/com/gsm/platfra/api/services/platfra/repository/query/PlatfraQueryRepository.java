/*
 *
 *  * Copyright (C) Hanwha Systems Ltd., 2021. All rights reserved.
 *  *
 *  * This software is covered by the license agreement between
 *  * the end user and Hanwha Systems Ltd., and may be
 *  * used and copied only in accordance with the terms of the
 *  * said agreement.
 *  *
 *  * Hanwha Systems Ltd., assumes no responsibility or
 *  * liability for any errors or inaccuracies in this software,
 *  * or any consequential, incidental or indirect damage arising
 *  * out of the use of the software.
 *
 */

package com.gsm.platfra.api.services.platfra.repository.query;

import com.gsm.platfra.api.entity.platfra.TPlatfra;
import com.gsm.platfra.api.services.platfra.dto.PlatfraResultDto;
import com.gsm.platfra.api.services.platfra.dto.PlatfraSearchDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.gsm.platfra.api.entity.platfra.QTPlatfra.tPlatfra;
import static com.gsm.platfra.api.entity.platfra.QTPlatfraCategory.tPlatfraCategory;


/**
 *
 */
@Repository
@RequiredArgsConstructor
public class PlatfraQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<PlatfraResultDto> getList(PlatfraSearchDto platfraSearchDto) {
        List<PlatfraResultDto> list = queryFactory
            .select(
                Projections.fields(
                    PlatfraResultDto.class,
                    tPlatfra.platfraId,
                    tPlatfra.subject,
                    tPlatfra.description,
                    tPlatfra.introduction,
                    tPlatfraCategory.id.categoryCd
                )
            )
            .from(tPlatfra)
            .join(tPlatfraCategory)
            .on(tPlatfra.platfraId.eq(tPlatfraCategory.id.platfraId))
            .where(
                platfraSearchDto.getPlatfraId().isBlank() ? null : tPlatfra.platfraId.eq(platfraSearchDto.getPlatfraId()),
                platfraSearchDto.getSubject().isBlank() ? null : tPlatfra.subject.contains(platfraSearchDto.getSubject()),
                platfraSearchDto.getDescription().isBlank() ? null : tPlatfra.description.contains(platfraSearchDto.getDescription()),
                platfraSearchDto.getIntroduction().isBlank() ? null : tPlatfra.introduction.contains(platfraSearchDto.getIntroduction())
            )
            .fetch()
            ;
        
        return list;
    }
}























