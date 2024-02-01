package com.gsm.platfra.api.common.rest;

import com.gsm.platfra.api.common.service.CommonCodeService;
import com.gsm.platfra.api.dto.common.CommonCodeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 공통코드 컨트롤러
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/common/code")
public class CommonCodeController {
    private final CommonCodeService commonCodeService;
    
    /**
     * 코드 리스트 조회
     * @param code 기준 코드
     * @param type 조회할 코드의 타입(CODE: 코드, PACKAGE: 패키지)
     * @return
     */
    @GetMapping
    public List<CommonCodeDto> getList(
        @RequestParam(required = true) String code,
        @RequestParam(required = true) String type
    ) {
        return commonCodeService.getList(code, type);
    }
    
    /**
     * 하위 포함 코드 리스트 조회
     * @param code 기준 코드
     * @return
     */
    @GetMapping("/all")
    public CommonCodeDto getAll(@RequestParam(required = true) String code) {
        return commonCodeService.getAll(code);
    }
    
}

