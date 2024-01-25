package com.gsm.platfra.api.services.file.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
public class FileUploadDto {

//    private List<MultipartFile> files;
    private String contentsCd;
    private Long contentsSeq;

}
