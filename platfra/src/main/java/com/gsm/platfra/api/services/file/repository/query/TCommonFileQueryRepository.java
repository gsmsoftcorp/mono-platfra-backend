package com.gsm.platfra.api.services.file.repository.query;

import com.gsm.platfra.api.dto.file.CommonFileDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.gsm.platfra.api.entity.common.QTCommonFile.tCommonFile;

@Repository
@RequiredArgsConstructor
public class TCommonFileQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<CommonFileDto> getList(CommonFileDto dto) {
        List<CommonFileDto> list = queryFactory
                .select(
                        Projections.fields(
                                CommonFileDto.class,
                                tCommonFile.contentsCd,
                                tCommonFile.contentsSeq,
                                tCommonFile.filePath,
                                tCommonFile.fileName,
                                tCommonFile.fileEncodingName,
                                tCommonFile.fileExtension,
                                tCommonFile.fileSize
                        )
                )
                .from(tCommonFile)
                .where(
                        dto.getContentsCd().isBlank() ? null : tCommonFile.contentsCd.eq(dto.getContentsCd()),
                        dto.getContentsSeq() == null ? null : tCommonFile.contentsSeq.eq(dto.getContentsSeq()),
                        tCommonFile.delYn.eq(Boolean.FALSE)
                )
                .fetch()
                ;

        return list;
    }

    public void delete(Long fileSeq) {
        queryFactory
                .update(tCommonFile)
                .set(tCommonFile.delYn, Boolean.TRUE)
                .where(tCommonFile.fileSeq.eq(fileSeq))
                .execute();
    }

    public CommonFileDto download(CommonFileDto dto) {
        return queryFactory
                .select(
                        Projections.fields(
                                CommonFileDto.class,
                                tCommonFile.contentsCd,
                                tCommonFile.contentsSeq,
                                tCommonFile.filePath,
                                tCommonFile.fileName,
                                tCommonFile.fileEncodingName,
                                tCommonFile.fileExtension,
                                tCommonFile.fileSize
                        )
                )
                .from(tCommonFile)
                .where(
                        dto.getContentsCd().isBlank() ? null : tCommonFile.contentsCd.eq(dto.getContentsCd()),
                        dto.getContentsSeq() == null ? null : tCommonFile.contentsSeq.eq(dto.getContentsSeq()),
                        dto.getFileSeq() == null ? null : tCommonFile.fileSeq.eq(dto.getFileSeq()),
                        tCommonFile.delYn.eq(Boolean.FALSE)
                )
                .fetchOne();
    }
}
