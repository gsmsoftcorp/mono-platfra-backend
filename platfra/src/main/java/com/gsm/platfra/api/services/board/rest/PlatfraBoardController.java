package com.gsm.platfra.api.services.board.rest;

import com.gsm.platfra.api.data.platfraboard.PlatfraBoardDto;
import com.gsm.platfra.api.services.board.dto.PlatfraBoardResDto;
import com.gsm.platfra.api.services.board.service.PlatfraBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    * @param platfraBoardDto
    * @return platfraBoardDtoList
    **/
    @GetMapping
    public List<PlatfraBoardDto> getList(PlatfraBoardDto platfraBoardDto){
        log.info("GetAllBoardList start. {}",platfraBoardDto);
        log.info("Request : {}",platfraBoardDto);
        return platfraBoardService.getList(platfraBoardDto);
    }

    /**
    * Board 디테일 조회
    * @param platfraBoardSeq
    * @return PlatfraBoardResDto
    * */
    @GetMapping("/{platfraBoardSeq}")
    public PlatfraBoardResDto getBoardDetail(@PathVariable(value = "platfraBoardSeq")long platfraBoardSeq){
        log.info("GetBoardDetail start. {}",platfraBoardSeq);
        log.info("Path : {}",platfraBoardSeq);
        return platfraBoardService.getPlatfraBoardDetail(platfraBoardSeq);
    }

    /**
    * Board 등록
    * @param platfraBoardDto
    * @return platfraBoardResDto
    * */
    @PostMapping
    public PlatfraBoardResDto create(@RequestBody PlatfraBoardDto platfraBoardDto){
        log.info("Create board start.");
        log.info("Request : {}",platfraBoardDto);
        return platfraBoardService.create(platfraBoardDto);
    }

    /**
    * Board 수정
    * @param platfraBoardDto
    * @return platfraBoardResDto
    * */
    @PatchMapping
    public PlatfraBoardResDto update(@RequestBody PlatfraBoardDto platfraBoardDto){
        log.info("Update board start.");
        log.info("Request : {}",platfraBoardDto);
        return platfraBoardService.update(platfraBoardDto);
    }

    /**
    * Board 삭제
    * @param
    * @return
    * */
    @DeleteMapping
    public boolean delete(@RequestBody PlatfraBoardDto platfraBoardDto){
        log.info("Delete board start.");
        log.info("Request : {}",platfraBoardDto);
        return platfraBoardService.delete(platfraBoardDto);
    }
}
