package com.gsm.platfra.api.services.file.service;

import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.gsm.platfra.api.entity.common.TCommonFile;
import com.gsm.platfra.api.services.file.dto.*;
import com.gsm.platfra.api.services.file.repository.TCommonFileRepository;
import com.gsm.platfra.api.services.file.repository.query.TCommonFileQueryRepository;
import com.gsm.platfra.common.util.S3FileComponent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class FileService {

    private final S3FileComponent s3FileComponent;
    private final TCommonFileRepository tCommonFileRepository;
    private final TCommonFileQueryRepository tCommonFileQueryRepository;

    public void upload(MultipartFile[] multipleFile, String contentsCd, Long contentsSeq) throws IOException { // 객체 업로드
        for (MultipartFile file : multipleFile) {
            FileInfoDto fileInfoDto = s3FileComponent.upload(file);
            TCommonFile tCommonFile = FileInfoDto.toEntity(fileInfoDto);
            tCommonFile.setContentsInfo(contentsCd, contentsSeq);
            /////////////
            tCommonFile.setModDate(Instant.now());
            tCommonFile.setModUserId("");
            tCommonFile.setRegDate(Instant.now());
            tCommonFile.setRegUserId("");
            /////////////
            tCommonFileRepository.save(tCommonFile);
        }
    }
    public List<FileResultDto> list(FileListReqDto fileListReqDto) {
        List<FileResultDto> list = tCommonFileQueryRepository.getList(fileListReqDto);
        return list;
    }

    public void delete(FileDeleteReqDto fileDeleteReqDto) {
        TCommonFile tCommonFile = tCommonFileRepository.findById(fileDeleteReqDto.getFileSeq()).orElseThrow();
        tCommonFileQueryRepository.deleteFile(tCommonFile);
    }

    public FileDownloadDto download(FileDownloadReqDto fileDownloadReqDto) {
        TCommonFile tCommonFile = tCommonFileRepository.findById(fileDownloadReqDto.getFileSeq()).orElseThrow();
        return FileDownloadDto.builder()
                .s3ObjectInputStream(s3FileComponent.download(tCommonFile.getFilePath()))
                .fileExtenton(tCommonFile.getFileExtension())
                .fileName(tCommonFile.getFileName())
                .build();
    }

}
