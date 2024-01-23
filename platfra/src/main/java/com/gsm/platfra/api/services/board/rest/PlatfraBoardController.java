package com.gsm.platfra.api.services.board.rest;

import com.gsm.platfra.api.services.board.dto.table.PlatfraBoardDto;
import com.gsm.platfra.api.services.board.service.PlatfraBoardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
z
    * @param platfraBoardDto
    * @return
    **/
    @PostMapping
    public List<PlatfraBoardDto> getList(PlatfraBoardDto platfraBoardDto){
        log.info("GetAllBoardList start.",platfraBoardDto);
        return platfraBoardService.getList(platfraBoardDto);
    }

    @GetMapping("/{platfraId}")
    public List<PlatfraBoardDto> getPlatfraBoardList(@PathVariable(value = "platfraId")String platfraId){
        log.info("GetPlatfraBoardList start. platfraId : {}",platfraId);
        return platfraBoardService.getPlatfraBoardList(platfraId);
    }

    @GetMapping("/detail/{platfraBoardSeq}")
    public PlatfraBoardDto getBoardDetail(@PathVariable(value = "platfraBoardSeq")long platfraBoardSeq){
        return platfraBoardService.getPlatfraBoardDetail(platfraBoardSeq);
    }

}
