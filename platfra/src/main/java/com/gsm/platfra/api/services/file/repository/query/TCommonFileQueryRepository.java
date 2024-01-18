package com.gsm.platfra.api.services.file.repository.query;

import com.gsm.platfra.api.entity.common.QTCommonFile;
import com.gsm.platfra.api.entity.common.TCommonFile;
import com.gsm.platfra.api.services.file.dto.FileListReqDto;
import com.gsm.platfra.api.services.file.dto.FileResultDto;
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

    public List<FileResultDto> getList(FileListReqDto fileListReqDto) {
        List<FileResultDto> list = queryFactory
                .select(
                        Projections.fields(
                                FileResultDto.class,
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
                        fileListReqDto.getContentsCd().isBlank() ? null : tCommonFile.contentsCd.eq(fileListReqDto.getContentsCd()),
                        fileListReqDto.getContentsSeq() == null ? null : tCommonFile.contentsSeq.eq(fileListReqDto.getContentsSeq())
                )
                .fetch()
                ;

        return list;
    }

    public void deleteFile(TCommonFile file) {
        queryFactory
                .update(tCommonFile)
                .set(tCommonFile.delYn, 'Y')
                .where(tCommonFile.id.eq(file.getId()))
                .execute();
    }
}
