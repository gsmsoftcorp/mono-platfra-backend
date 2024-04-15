package com.gsm.platfra.api.services.platfra.rest;

import com.gsm.platfra.api.data.base.BaseResponse;
import com.gsm.platfra.api.services.platfra.service.ContentSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/platfra/{platfraId}")
@RestController
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

        // Todo : return data
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

        // Todo : return data
    }
}
