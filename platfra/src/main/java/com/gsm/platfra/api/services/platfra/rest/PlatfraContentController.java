package com.gsm.platfra.api.services.platfra.rest;

import com.gsm.platfra.api.data.base.BaseResponse;
import com.gsm.platfra.api.data.platfra.PlatfraContentDto;
import com.gsm.platfra.api.services.platfra.service.PlatfraContentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


/**
 * 플랫프라 마스터 컨트롤러
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/platfra/content")
public class PlatfraContentController {
    private final PlatfraContentService platfraContentService;
    
    /**
     * 컨텐츠 리스트 조회
     * @param platfraId
     * @return
     */
    @GetMapping
    public BaseResponse getList(@RequestParam(required = true) String platfraId) {
        return BaseResponse.builder()
            .data(platfraContentService.getList(platfraId))
            .build();
    }
    
    /**
     * 컨텐츠 메인(상세) 조회
     * @param contentSeq
     * @return
     */
    @GetMapping("/{contentSeq}")
    public BaseResponse get(@PathVariable(required = true) Long contentSeq) {
        return BaseResponse.builder()
            .data(platfraContentService.get(contentSeq))
            .build();
    }
    
    /**
     * 컨텐츠 등록
     * @param platfraContentDto
     */
    @PostMapping
    public BaseResponse create(@RequestBody PlatfraContentDto platfraContentDto) {
        platfraContentService.create(platfraContentDto);
        return BaseResponse.builder()
            .build();
    }
    
    /**
     * 컨텐츠 수정
     * @param platfraContentDto
     */
    @PutMapping
    public BaseResponse update(@RequestBody PlatfraContentDto platfraContentDto) {
        platfraContentService.update(platfraContentDto);
        return BaseResponse.builder()
            .build();
    }
    
    /**
     * 컨텐츠 삭제
     * @param platfraContentDto
     */
    @DeleteMapping
    public BaseResponse delete(@RequestBody PlatfraContentDto platfraContentDto) {
        platfraContentService.delete(platfraContentDto);
        return BaseResponse.builder()
            .build();
    }
}

