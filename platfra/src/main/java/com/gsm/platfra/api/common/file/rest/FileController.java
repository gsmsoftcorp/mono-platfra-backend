package com.gsm.platfra.api.common.file.rest;

import com.gsm.platfra.api.common.file.dto.FileDownloadDto;
import com.gsm.platfra.api.common.file.service.FileService;
import com.gsm.platfra.api.data.base.BaseResponse;
import com.gsm.platfra.api.data.common.file.CommonFileDto;
import com.gsm.platfra.util.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    // TODO: 특정 확장자만 받을 수 있도록 추가 구현
    @PostMapping(value = "/upload", consumes = "multipart/*")
    public BaseResponse upload(@RequestPart("file") List<MultipartFile> file, @RequestPart("dto") CommonFileDto commonFileDto) throws IOException {
        fileService.upload(file, commonFileDto);
        return BaseResponse.builder()
                .data(null)
                .code(null)
                .message(null)
                .error(null)
                .build();
    }

    @GetMapping("/{contentsCd}/{contentsSeq}")
    public BaseResponse list(@PathVariable String contentsCd, @PathVariable Long contentsSeq) {
        return BaseResponse.builder()
                .data(fileService.list(contentsCd, contentsSeq))
                .code(null)
                .message(null)
                .error(null)
                .build();
    }

    @DeleteMapping
    public BaseResponse delete(@RequestBody CommonFileDto commonFileDto) {
        fileService.delete(commonFileDto);
        return BaseResponse.builder()
                .data(null)
                .code(null)
                .message(null)
                .error(null)
                .build();
    }

    @GetMapping(value = "/download")
    public ResponseEntity<InputStreamResource> download(@RequestBody CommonFileDto commonFileDto) {
        FileDownloadDto downloadDto = fileService.download(commonFileDto);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(CommonUtils.contentType(downloadDto.getFileExtenton()));
        headers.setContentDispositionFormData("attachment", URLEncoder.encode(downloadDto.getFileName(), StandardCharsets.UTF_8));

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(downloadDto.getS3ObjectInputStream()));
    }
}
