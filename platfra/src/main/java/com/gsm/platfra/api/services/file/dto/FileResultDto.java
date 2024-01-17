package com.gsm.platfra.api.services.file.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
public class FileResultDto {

    private String contentsCd;
    private Long contentsSeq;
    private String filePath;
    private String fileName;
    private String fileEncodingName;
    private String fileExtension;
    private String fileSize;

}
