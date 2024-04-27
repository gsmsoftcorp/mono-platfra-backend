package com.gsm.platfra.api.services.platfra.rest;

import com.gsm.platfra.api.data.base.BaseResponse;
import com.gsm.platfra.api.data.platfra.PlatfraDto;
import com.gsm.platfra.api.services.platfra.dto.PlatfraMainDto;
import com.gsm.platfra.api.services.platfra.service.PlatfraService;
import com.gsm.platfra.system.security.context.UserContext;
import com.gsm.platfra.system.security.context.UserContextUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 플랫폼 마스터 컨트롤러
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/platfra")
public class PlatfraController {
    private final PlatfraService platfraService;
    /**
     * 플랫폼 검색 리스트 조회
     * @param searchValue
     * @return
     */
    @GetMapping("/search")
    public BaseResponse getSearchList(@RequestParam(required = false) String searchValue) {
        UserContext userContext = UserContextUtil.getUserContext();
        return BaseResponse.builder()
                .data(platfraService.getSearchList(searchValue))
                .code(null)
                .message(null)
                .error(null)
                .build();
    }

    /**
     * 플랫폼 리스트 조회
     * @param platfraDto
     * @return
     */
    @GetMapping
    public BaseResponse getList(PlatfraDto platfraDto) {
        UserContext userContext = UserContextUtil.getUserContext();
        return BaseResponse.builder()
                .data(platfraService.getList(platfraDto))
                .code(null)
                .message(null)
                .error(null)
                .build();
    }

    /**
     * 플랫폼 상세(메인) 조회
     * @param platfraId
     * @return
     */
    @GetMapping("/{platfraId}")
    public BaseResponse get(@PathVariable(required = true) String platfraId) {
        return BaseResponse.builder()
            .data(platfraService.get(platfraId))
            .code(null)
            .message(null)
            .error(null)
            .build();
    }

    /**
     * 플랫폼 상세(메인) 조회
     * @param platfraSeq
     * @return
     */
    @GetMapping("/{platfraSeq}")
    public BaseResponse getDetail(@RequestParam(required = true) Long platfraSeq) {
        return BaseResponse.builder()
            .data(platfraService.get(platfraSeq))
            .code(null)
            .message(null)
            .error(null)
            .build();
    }

    /**
     * 플랫폼 등록
     * @param platfraDto
     */
    @PostMapping
    public BaseResponse create(@RequestBody PlatfraDto platfraDto) {
        platfraService.create(platfraDto);
        return BaseResponse.builder()
                .data(null)
                .code(null)
                .message(null)
                .error(null)
                .build();
    }
    
    /**
     * 플랫폼 수정
     * @param platfraDto
     */
    @PutMapping
    public BaseResponse update(@RequestBody PlatfraDto platfraDto) {
        platfraService.update(platfraDto);
        return BaseResponse.builder()
                .data(null)
                .code(null)
                .message(null)
                .error(null)
                .build();
    }
    
    /**
     * 플랫폼 삭제
     * @param platfraDto
     */
    @DeleteMapping
    public BaseResponse delete(@RequestBody PlatfraDto platfraDto) {
        platfraService.delete(platfraDto);
        return BaseResponse.builder()
                .data(null)
                .code(null)
                .message(null)
                .error(null)
                .build();
    }
}

