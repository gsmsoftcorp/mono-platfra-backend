package com.gsm.platfra.api.services.board.rest;

import com.gsm.platfra.api.services.board.dto.table.PlatfraBoardDto;
import com.gsm.platfra.api.services.board.service.PlatfraBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* PlatfraBoardController
* 플랫프라 보드 컨트롤러
**/
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/platfra/board")
public class PlatfraBoardController {

    private final PlatfraBoardService platfraBoardService;

    /**
    * 플랫폼 리스트 조회
     *
    * @param platfraBoardDto
    * @return
    **/
    @GetMapping
    public List<PlatfraBoardDto> getList(@RequestBody(required = false) PlatfraBoardDto platfraBoardDto){
        log.info("PlatfraBoardDto : ",platfraBoardDto);
        return platfraBoardService.getList(platfraBoardDto);
    }


}
