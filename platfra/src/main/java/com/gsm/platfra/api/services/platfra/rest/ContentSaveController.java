package com.gsm.platfra.api.services.platfra.rest;

import com.gsm.platfra.api.data.base.BaseResponse;
import com.gsm.platfra.api.services.platfra.service.ContentSaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/platfra/{platfraId}")
public class ContentSaveController {

    private final ContentSaveService contentSaveService;

    @PostMapping("/{platfraBoardSeq}/{contentSeq}")
    public BaseResponse saveContent(
            @PathVariable String platfraId,
            @PathVariable Long platfraBoardSeq,
            @PathVariable Long contentSeq
    ) {
        contentSaveService.saveContent(platfraId, platfraBoardSeq, contentSeq);
        return BaseResponse.builder()
                .data(null)
                .code(null)
                .message(null)
                .error(null)
                .build();
    }

    @PostMapping("/{contentSeq}")
    public BaseResponse saveContent(@PathVariable String platfraId, @PathVariable Long contentSeq) {
        contentSaveService.saveContent(platfraId, contentSeq);
        return BaseResponse.builder()
                .data(null)
                .code(null)
                .message(null)
                .error(null)
                .build();
    }
}
