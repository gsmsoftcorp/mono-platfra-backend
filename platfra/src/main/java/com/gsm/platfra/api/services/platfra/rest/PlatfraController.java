package com.gsm.platfra.api.services.platfra.rest;

import com.gsm.platfra.api.dto.platfra.PlatfraDto;
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
     * 플랫폼 리스트 조회
     * @param platfraDto
     * @return
     */
    @GetMapping
    public List<PlatfraDto> getList(PlatfraDto platfraDto) {
        UserContext userContext = UserContextUtil.getUserContext();
        log.debug("userContext111111 : ", userContext);
        return platfraService.getList(platfraDto);
    }
    
    /**
     * 플랫폼 메인(상세) 조회
     * @param platfraSeq
     * @return
     */
    @GetMapping("/{platfraSeq}")
    public PlatfraMainDto get(@PathVariable(required = true) Long platfraSeq) {
        return platfraService.get(platfraSeq);
    }
    
    /**
     * 플랫폼 등록
     * @param platfraDto
     */
    @PostMapping
    public void create(@RequestBody PlatfraDto platfraDto) {
        platfraService.create(platfraDto);
    }
    
    /**
     * 플랫폼 수정
     * @param platfraDto
     */
    @PutMapping
    public void update(@RequestBody PlatfraDto platfraDto) {
        platfraService.update(platfraDto);
    }
    
    /**
     * 플랫폼 삭제
     * @param platfraDto
     */
    @DeleteMapping
    public void delete(@RequestBody PlatfraDto platfraDto) {
        platfraService.delete(platfraDto);
    }
}

