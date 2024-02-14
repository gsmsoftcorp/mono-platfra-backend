package com.gsm.platfra.api.common.error.service;

import com.gsm.platfra.api.data.common.error.CommonErrorDto;
import com.gsm.platfra.api.data.common.error.TCommonError;
import com.gsm.platfra.api.data.common.error.TCommonErrorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.WebRequest;

/**
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 필수!! 실수방지
public class CommonErrorService {
    private final TCommonErrorRepository tCommonErrorRepository;

    @Transactional
    public void errorLogging(CommonErrorDto commonErrorDto) {
        TCommonError tCommonError = CommonErrorDto.toEntity(commonErrorDto);
        tCommonErrorRepository.save(tCommonError);
    }

    @Transactional
    public void errLogUpdate(Object o){
        if (o instanceof ResponseEntity) {
            ResponseEntity<?> response = (ResponseEntity<?>) o;
            // 여기서 response의 본문, 상태 코드 등을 확인하고 로깅할 수 있습니다.
            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Response Body: " + response.getBody());
        }
    }
}
