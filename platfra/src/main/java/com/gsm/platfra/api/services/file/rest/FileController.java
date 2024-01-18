package com.gsm.platfra.api.services.file.rest;

import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.gsm.platfra.api.services.file.dto.*;
import com.gsm.platfra.api.services.file.service.FileService;
import com.gsm.platfra.common.util.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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

    @PostMapping(value = "/upload/{contentsCd}/{contentsSeq}", consumes = "multipart/*")
    public void upload(@RequestPart("files") MultipartFile[] files, @PathVariable("contentsCd") String contentsCd, @PathVariable("contentsSeq") Long contentsSeq) throws IOException {
        fileService.upload(files, contentsCd, contentsSeq);
    }

    @GetMapping(value = "/list")
    public List<FileResultDto> list(@RequestParam("cd") String contentsCd, @RequestParam("seq") Long contentsSeq) {

        return fileService.list(FileListReqDto.builder()
                .contentsCd(contentsCd)
                .contentsSeq(contentsSeq)
                .build());
    }

    @PostMapping("/delete")
    public void delete(@RequestBody FileDeleteReqDto fileDeleteReqDto) {
        fileService.delete(fileDeleteReqDto);
    }

    @GetMapping(value = "/download")
    public ResponseEntity<InputStreamResource> download(@RequestBody FileDownloadReqDto fileDownloadReqDto) {
        FileDownloadDto downloadDto = fileService.download(fileDownloadReqDto);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(CommonUtils.contentType(downloadDto.getFileExtenton()));
        headers.setContentDispositionFormData("attachment", URLEncoder.encode(downloadDto.getFileName(), StandardCharsets.UTF_8));

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(downloadDto.getS3ObjectInputStream()));
    }


}
