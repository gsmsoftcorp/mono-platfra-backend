package com.gsm.platfra.util;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.gsm.platfra.api.data.common.file.CommonFileDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class S3FileComponent { // S3 연동 - 업로드, 삭제, 다운로드

    private final AmazonS3Client amazonS3Client; // aws정보가 담겨있는 client

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public CommonFileDto upload(MultipartFile file) throws IOException { // 객체 업로드
        // 파일의 확장자 추출
        String fileExtension = getFileExtension(file.getOriginalFilename());

        // TODO: 파일명 중복을 피하기위해 UUID 추가했는데, 논의 필요
        String fileEncodingName = UUID.randomUUID() + "_" + URLEncoder.encode(file.getOriginalFilename(), StandardCharsets.UTF_8);
        // put - S3로 업로드
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());

        // 경로 + fileName
        // TODO: 경로 세팅 필요
        amazonS3Client.putObject(bucket, "service/" + "board/" + fileEncodingName, file.getInputStream(), metadata);

        log.debug("File Extension : " + fileExtension);
        log.debug("File Name : " + file.getOriginalFilename());
        log.debug("Upload File Name : " + fileEncodingName);
        log.debug("File Size : " + file.getSize());

        // TODO: filePath 세팅 필요
        return CommonFileDto.builder()
                .filePath("service/board/" + fileEncodingName)
                .fileSize(String.valueOf(file.getSize()))
                .fileName(file.getOriginalFilename())
                .fileEncodingName(fileEncodingName)
                .fileExtension(fileExtension)
                .build();
    }

    public S3ObjectInputStream download(String filePath) {
        S3Object s3Object = amazonS3Client.getObject(bucket, filePath);

        return s3Object.getObjectContent();
    }

    public static String getFileExtension(String fileName) {
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return ""; // 확장자가 없는 경우 빈 문자열 반환
        }
    }
}