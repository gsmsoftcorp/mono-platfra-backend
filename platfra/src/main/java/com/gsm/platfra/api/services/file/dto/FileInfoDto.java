package com.gsm.platfra.api.services.file.dto;

import com.gsm.platfra.api.entity.common.TCommonFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FileInfoDto {
    // 파일 경로, 원본 파일명, 인코딩된 파일명(s3 저장된 파일명), 확장자, 크기
    private String filePath;
    private String fileName;
    private String fileEncodingName;
    private String fileExtension;
    private String fileSize;

    public static TCommonFile toEntity(FileInfoDto dto) {
        return TCommonFile.builder()
                .fileName(dto.fileName)
                .filePath(dto.filePath)
                .fileSize(dto.fileSize)
                .fileEncodingName(dto.fileEncodingName)
                .fileExtension(dto.fileExtension)
                .build();

    }
}
