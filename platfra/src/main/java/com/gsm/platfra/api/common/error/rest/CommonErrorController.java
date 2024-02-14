package com.gsm.platfra.api.common.error.rest;

import com.gsm.platfra.api.common.error.service.CommonErrorService;
import com.gsm.platfra.api.data.common.code.CommonCodeDto;
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
@RequestMapping("/common/error")
public class CommonErrorController {
    private final CommonErrorService commonErrorService;
}

