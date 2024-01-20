package com.gsm.platfra.api.services.file.dto;

import com.amazonaws.services.s3.model.S3ObjectInputStream;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FileDownloadDto {
    private S3ObjectInputStream s3ObjectInputStream;
    private String fileExtenton;
    private String fileName;
}
