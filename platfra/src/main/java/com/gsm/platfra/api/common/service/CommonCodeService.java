package com.gsm.platfra.api.common.service;

import com.gsm.platfra.api.common.repository.TCommonCodeRepository;
import com.gsm.platfra.api.dto.common.CommonCodeDto;
import com.gsm.platfra.api.entity.common.TCommonCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 필수!! 실수방지
public class CommonCodeService {
    private final TCommonCodeRepository tCommonCodeRepository;
    
    public List<CommonCodeDto> getList(String code, String type) {
        List<TCommonCode> commonCodeList = tCommonCodeRepository.findAllByParentCdAndTypeAndDelYn(code, type, false);
        List<CommonCodeDto> commonCodeDtoList = commonCodeList.stream().map(CommonCodeDto::of).collect(Collectors.toList());
        
        return commonCodeDtoList;
    }
    
    public CommonCodeDto getAll(String code) {
        TCommonCode tCommonCode = tCommonCodeRepository.findById(code).filter(entity -> entity.getDelYn().equals(Boolean.FALSE)).orElseThrow(/*DataNotFoundException::new*/);
        List<TCommonCode> childCodeList = tCommonCode.getChildCodeList().stream()
            .filter(childCode -> childCode.getDelYn().equals(Boolean.FALSE))
            .collect(Collectors.toList());
        
        CommonCodeDto commonCodeDto = CommonCodeDto.of(tCommonCode);
        List<CommonCodeDto> commonCodeDtoList = new ArrayList<>();
        childCodeList.forEach(childCode -> {
            CommonCodeDto childCodeDto = CommonCodeDto.of(childCode);
            if(childCode.getType().equals("PACKAGE")) {
                List<CommonCodeDto> childCodeDtoList = childCode.getChildCodeList().stream()
                    .filter(childCode2 -> childCode2.getDelYn().equals(Boolean.FALSE))
                    .map(CommonCodeDto::of)
                    .collect(Collectors.toList());
                childCodeDto.getChildCodeDtoList().addAll(childCodeDtoList);
            }
            commonCodeDtoList.add(childCodeDto);
            
        });
        
        commonCodeDto.getChildCodeDtoList().addAll(commonCodeDtoList);
        
        return commonCodeDto;
    }
    
}
