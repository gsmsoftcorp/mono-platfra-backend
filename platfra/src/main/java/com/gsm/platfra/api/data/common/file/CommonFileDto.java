package com.gsm.platfra.api.data.common.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CommonFileDto {
    private Long fileSeq;
    private String contentsCd;
    private Long contentsSeq;
    private String filePath;
    private String fileName;
    private String fileEncodingName;
    private String fileExtension;
    private String fileSize;
    private Boolean delYn;

    public static TCommonFile toEntity(CommonFileDto dto) {
        return TCommonFile.builder()
                .fileSeq(dto.fileSeq)
                .contentsCd(dto.contentsCd)
                .contentsSeq(dto.contentsSeq)
                .filePath(dto.filePath)
                .fileName(dto.fileName)
                .fileEncodingName(dto.fileEncodingName)
                .fileExtension(dto.fileExtension)
                .fileSize(dto.fileSize)
                .build();
    }

    public static CommonFileDto of(TCommonFile tCommonFile) {
        return CommonFileDto.builder()
                .fileSeq(tCommonFile.getFileSeq())
                .contentsCd(tCommonFile.getContentsCd())
                .contentsSeq(tCommonFile.getFileSeq())
                .filePath(tCommonFile.getFilePath())
                .fileName(tCommonFile.getFileName())
                .fileEncodingName(tCommonFile.getFileName())
                .fileExtension(tCommonFile.getFileExtension())
                .fileSize(tCommonFile.getFileSize())
                .delYn(tCommonFile.getDelYn())
                .build();
    }

    public void setContentsInfo(String contentsCd, long contentsSeq) {
        this.contentsCd = contentsCd;
        this.contentsSeq = contentsSeq;
    }
}
