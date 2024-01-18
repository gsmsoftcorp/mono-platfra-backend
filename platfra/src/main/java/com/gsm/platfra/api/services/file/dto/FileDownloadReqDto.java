package com.gsm.platfra.api.services.file.dto;

import lombok.Getter;

@Getter
public class FileDownloadReqDto {

    private Long fileSeq;
    private String contentsCd;
    private Long contentsSeq;

}
