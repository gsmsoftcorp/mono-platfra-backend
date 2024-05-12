package com.gsm.platfra.api.common.file.service;

import com.gsm.platfra.api.common.file.repository.TCommonFileRepository;
import com.gsm.platfra.api.data.platfra.PlatfraDto;
import com.gsm.platfra.api.data.common.file.TCommonFile;
import com.gsm.platfra.api.data.platfra.TPlatfra;
import com.gsm.platfra.api.common.file.dto.FileDownloadDto;
import com.gsm.platfra.api.data.common.file.CommonFileDto;
import com.gsm.platfra.api.common.file.repository.query.TCommonFileQueryRepository;
import com.gsm.platfra.api.data.platfra.TPlatfraRepository;
import com.gsm.platfra.util.S3FileComponent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class FileService {

    private final S3FileComponent s3FileComponent;
    private final TCommonFileRepository tCommonFileRepository;
    private final TCommonFileQueryRepository tCommonFileQueryRepository;
    private final TPlatfraRepository tPlatfraRepository;

    @Transactional
    public void upload(List<MultipartFile> files, CommonFileDto fileDto) throws IOException { // 객체 업로드

        // contentsCd 를 확인해서 어떤 테이블을 읽어올지 알아야되는데, 어떤식으로 확인해야 되는지?
        TPlatfra tPlatfra = tPlatfraRepository.findById(fileDto.getContentsSeq()).orElseThrow(/*DataNotFoundException::new*/);
        PlatfraDto platfraDto = PlatfraDto.of(tPlatfra);

//        for (MultipartFile file : fileUploadDto.getFiles()) {
        for (MultipartFile file : files) {
            CommonFileDto commonFileDto = s3FileComponent.upload(file);
            // contentsCd 수정 필요
            commonFileDto.setContentsInfo(fileDto.getContentsCd(), platfraDto.getPlatfraSeq());
            TCommonFile tCommonFile = CommonFileDto.toEntity(commonFileDto);

            tCommonFileRepository.save(tCommonFile);
        }
    }
    public List<CommonFileDto> list(String contentsCd, Long contentsSeq) {
        CommonFileDto commonFileDto = CommonFileDto.builder()
                .contentsCd(contentsCd)
                .contentsSeq(contentsSeq)
                .build();

        // TODO: content cd, seq 검증 로직 추가 필요
        List<CommonFileDto> list = tCommonFileQueryRepository.getList(commonFileDto);
        return list;
    }

    @Transactional
    public void delete(CommonFileDto commonFileDto) {
        tCommonFileRepository.findById(commonFileDto.getFileSeq()).orElseThrow();
        tCommonFileQueryRepository.delete(commonFileDto.getFileSeq());
    }

    public FileDownloadDto download(CommonFileDto commonFileDto) {
        // TODO: 존재하는 파일인지 검증 후 가져온 entity를 그대로 사용? dto로 변환해서 사용?
        CommonFileDto fileDto = tCommonFileQueryRepository.download(commonFileDto);
        if (fileDto == null) {
//            throw new ;
        }
        return FileDownloadDto.builder()
                .s3ObjectInputStream(s3FileComponent.download(fileDto.getFilePath()))
                .fileExtenton(fileDto.getFileExtension())
                .fileName(fileDto.getFileName())
                .build();
    }
}
