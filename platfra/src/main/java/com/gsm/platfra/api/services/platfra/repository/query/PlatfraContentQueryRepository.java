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

import com.gsm.platfra.api.dto.platfra.PlatfraContentDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.gsm.platfra.api.entity.platfra.QTPlatfraContent.tPlatfraContent;


/**
 *
 */
@Repository
@RequiredArgsConstructor
public class PlatfraContentQueryRepository {
    private final JPAQueryFactory queryFactory;
    
    public List<PlatfraContentDto> getList(String platfraId) {
        List<PlatfraContentDto> list = queryFactory
            .select(
                Projections.fields(
                    PlatfraContentDto.class,
                    tPlatfraContent.contentSeq,
                    tPlatfraContent.platfraId,
                    tPlatfraContent.title,
                    tPlatfraContent.content
                )
            )
            .from(tPlatfraContent)
            .where(
                tPlatfraContent.platfraId.eq(platfraId),
                tPlatfraContent.delYn.eq(Boolean.FALSE)
            )
            .orderBy(tPlatfraContent.regDate.desc())
            .fetch()
            ;
        
        return list;
        
    }
    
    public void delete(PlatfraContentDto platfraContentDto) {
        queryFactory
            .update(tPlatfraContent)
            .set(tPlatfraContent.delYn, Boolean.TRUE)
            .where(
                tPlatfraContent.contentSeq.eq(platfraContentDto.getContentSeq()),
                tPlatfraContent.platfraId.eq(platfraContentDto.getPlatfraId()),
                tPlatfraContent.contentNo.eq(platfraContentDto.getContentNo())
            )
            .execute()
        ;
    }
}























