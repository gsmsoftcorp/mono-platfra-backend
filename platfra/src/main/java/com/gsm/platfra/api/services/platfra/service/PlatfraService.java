package com.gsm.platfra.api.services.platfra.service;

import com.gsm.platfra.api.entity.platfra.TPlatfra;
import com.gsm.platfra.api.services.platfra.dto.PlatfraMainResDto;
import com.gsm.platfra.api.dto.platfra.PlatfraContentDto;
import com.gsm.platfra.api.dto.platfra.PlatfraDto;
import com.gsm.platfra.api.services.platfra.repository.TPlatfraRepository;
import com.gsm.platfra.api.services.platfra.repository.query.PlatfraQueryRepository;
import com.gsm.platfra.system.security.context.UserContext;
import com.gsm.platfra.system.security.context.UserContextUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 필수!! 실수방지
public class PlatfraService {
    private final TPlatfraRepository tPlatfraRepository;
    private final PlatfraQueryRepository platfraQueryRepository;
    
    public List<PlatfraDto> getList(PlatfraDto platfraDto) {
        UserContext userContext = UserContextUtil.getUserContext();
        log.debug("userContext : ", userContext);
        List<PlatfraDto> platfraDtoList = platfraQueryRepository.getList(platfraDto);
        
        return platfraDtoList;
    }
    
    public PlatfraMainResDto get(Long platfraSeq) {
        // note : 여러개의 데이터를 조합하여 응답할때는 [테이블명]DTO 즉, 테이블용 DTO로 조회하여 service 단에서 화면출력용 DTO에 세팅한다.
        PlatfraMainResDto platfraMainResDto = new PlatfraMainResDto();
        
        //1. 플랫폼 마스터 조회
        TPlatfra tPlatfra = tPlatfraRepository.findById(platfraSeq).orElseThrow(/*DataNotFoundException::new*/);
        PlatfraDto platfraDto = PlatfraDto.of(tPlatfra);
        platfraMainResDto.setPlatfraDto(platfraDto);
        
        //2. 최신 컨텐츠 조회
        List<PlatfraContentDto> latestContentList = platfraQueryRepository.getLatestContentList(platfraDto.getPlatfraId());
        platfraMainResDto.setLatestContentList(latestContentList);
        
        //3. 인기 컨텐츠 조회
        List<PlatfraContentDto> hotContentList = platfraQueryRepository.getHotContentList(platfraDto.getPlatfraId());
        platfraMainResDto.setHotContentList(hotContentList);
        
        return platfraMainResDto;
    }
    
    @Transactional
    public void create(PlatfraDto platfraDto) {
        TPlatfra tPlatfra = TPlatfra.builder()
            .platfraId(platfraDto.getPlatfraId())
            .subject(platfraDto.getSubject())
            .description(platfraDto.getDescription())
            .introduction(platfraDto.getIntroduction())
            .ownerId(platfraDto.getOwnerId())
            .build();
        
        tPlatfraRepository.saveAndFlush(tPlatfra);
    }
    
    @Transactional
    public void update(PlatfraDto platfraDto) {
        TPlatfra tPlatfra = tPlatfraRepository.findById(platfraDto.getPlatfraSeq()).orElseThrow(/*DataNotFoundException::new*/);
        tPlatfra.update(platfraDto);
        
        tPlatfraRepository.flush();
    }
    
    @Transactional
    public void delete(PlatfraDto platfraDto) {
        platfraQueryRepository.delete(platfraDto.getPlatfraSeq());
    }
    
}
