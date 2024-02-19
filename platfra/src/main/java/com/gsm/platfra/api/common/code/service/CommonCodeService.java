package com.gsm.platfra.api.common.code.service;

import com.gsm.platfra.api.data.common.code.CommonCodeEnum;
import com.gsm.platfra.api.data.common.code.TCommonCodeRepository;
import com.gsm.platfra.api.data.common.code.CommonCodeDto;
import com.gsm.platfra.api.data.common.code.TCommonCode;
import com.gsm.platfra.api.data.platfra.TPlatfraContentRepository;
import com.gsm.platfra.api.data.platfra.TPlatfraRepository;
import com.gsm.platfra.api.data.platfraboard.TPlatfraBoardContentRepository;
import com.gsm.platfra.api.data.platfraboard.TPlatfraBoardRepository;
import com.gsm.platfra.api.features.dto.FeatureDto;
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

    private final TPlatfraRepository tPlatfraRepository;
    private final TPlatfraContentRepository tPlatfraContentRepository;
    private final TPlatfraBoardRepository tPlatfraBoardRepository;
    private final TPlatfraBoardContentRepository tPlatfraBoardContentRepository;
    
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

    public boolean checkUsableFeat(FeatureDto featureDto){

        String commonCd = featureDto.getCommonCd();
        if(commonCd.equals(CommonCodeEnum.CONTENTS.getCommonCd())){
            return !tPlatfraContentRepository.existsById(featureDto.getFeatureSeq());
        }else if(commonCd.equals(CommonCodeEnum.PLATFRA.getCommonCd())){
            return tPlatfraRepository.existsByPlatfraId(featureDto.getFeatureNo());
        }else if(commonCd.equals(CommonCodeEnum.PLATFRA_BOARD.getCommonCd())){
            return tPlatfraBoardRepository.existsById(featureDto.getFeatureSeq());
        }else if(commonCd.equals(CommonCodeEnum.PLATFRA_BOARD_CONTENT.getCommonCd())){
            return tPlatfraBoardContentRepository.existsById(featureDto.getFeatureSeq());
        }else if(commonCd.equals(CommonCodeEnum.PLATFRA_CONTENT.getCommonCd())){
            return tPlatfraContentRepository.existsById(featureDto.getFeatureSeq());
        }
        return false;
    }

    
}
