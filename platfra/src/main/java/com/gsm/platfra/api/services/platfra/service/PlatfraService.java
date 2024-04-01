package com.gsm.platfra.api.services.platfra.service;

import com.gsm.platfra.api.data.platfra.PlatfraContentDto;
import com.gsm.platfra.api.data.platfra.PlatfraDto;
import com.gsm.platfra.api.data.platfra.TPlatfra;
import com.gsm.platfra.api.services.platfra.dto.PlatfraMainDto;
import com.gsm.platfra.api.data.platfra.TPlatfraRepository;
import com.gsm.platfra.api.services.platfra.query.PlatfraQueryRepository;
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
    
    public PlatfraMainDto get(String platfraId) {
        // note : 여러개의 데이터를 조합하여 응답할때는 [테이블명]DTO 즉, 테이블용 DTO로 조회하여 service 단에서 화면출력용 DTO에 세팅한다.
        PlatfraMainDto platfraMainDto = new PlatfraMainDto();
        
        //1. 플랫폼 마스터 조회
        TPlatfra tPlatfra = tPlatfraRepository.findByPlatfraId(platfraId).orElseThrow(/*DataNotFoundException::new*/);
        PlatfraDto platfraDto = PlatfraDto.of(tPlatfra);
        platfraMainDto.setPlatfraDto(platfraDto);
        
        //2. 최신 컨텐츠 조회
        List<PlatfraContentDto> latestContentList = platfraQueryRepository.getLatestContentList(platfraDto.getPlatfraId());
        platfraMainDto.setLatestContentList(latestContentList);
        
        //3. 인기 컨텐츠 조회
        List<PlatfraContentDto> hotContentList = platfraQueryRepository.getHotContentList(platfraDto.getPlatfraId());
        platfraMainDto.setHotContentList(hotContentList);
        
        return platfraMainDto;
    }
    
    public List<PlatfraDto> getList(PlatfraDto platfraDto) {
        UserContext userContext = UserContextUtil.getUserContext();
        log.debug("userContext : {}", userContext);
        List<PlatfraDto> platfraDtoList = platfraQueryRepository.getList(platfraDto);
        
        return platfraDtoList;
    }
    
    public PlatfraMainDto get(Long platfraSeq) {
        // note : 여러개의 데이터를 조합하여 응답할때는 [테이블명]DTO 즉, 테이블용 DTO로 조회하여 service 단에서 화면출력용 DTO에 세팅한다.
        PlatfraMainDto platfraMainDto = new PlatfraMainDto();
        
        //1. 플랫폼 마스터 조회
        TPlatfra tPlatfra = tPlatfraRepository.findById(platfraSeq).orElseThrow(/*DataNotFoundException::new*/);
        PlatfraDto platfraDto = PlatfraDto.of(tPlatfra);
        platfraMainDto.setPlatfraDto(platfraDto);
        
        //2. 최신 컨텐츠 조회
        List<PlatfraContentDto> latestContentList = platfraQueryRepository.getLatestContentList(platfraDto.getPlatfraId());
        platfraMainDto.setLatestContentList(latestContentList);
        
        //3. 인기 컨텐츠 조회
        List<PlatfraContentDto> hotContentList = platfraQueryRepository.getHotContentList(platfraDto.getPlatfraId());
        platfraMainDto.setHotContentList(hotContentList);
        
        return platfraMainDto;
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
