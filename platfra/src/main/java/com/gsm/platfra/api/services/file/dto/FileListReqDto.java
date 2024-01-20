package com.gsm.platfra.api.services.file.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FileListReqDto {

    private String contentsCd;
    private Long contentsSeq;

}
