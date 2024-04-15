package com.gsm.platfra.api.services.board.rest;

import com.gsm.platfra.api.data.base.BaseResponse;
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
    public BaseResponse getList(PlatfraBoardDto platfraBoardDto){
        log.info("GetAllBoardList start. {}",platfraBoardDto);
        log.info("Request : {}",platfraBoardDto);
        return BaseResponse.builder()
                .data(platfraBoardService.getList(platfraBoardDto))
                .code(null)
                .message(null)
                .error(null)
                .build();
    }

    /**
    * Board 디테일 조회
    * @param platfraBoardSeq
    * @return PlatfraBoardResDto
    * */
    @GetMapping("/{platfraBoardSeq}")
    public BaseResponse getBoardDetail(@PathVariable(value = "platfraBoardSeq")long platfraBoardSeq){
        log.info("GetBoardDetail start. {}",platfraBoardSeq);
        log.info("Path : {}",platfraBoardSeq);
        return BaseResponse.builder()
                .data(platfraBoardService.getPlatfraBoardDetail(platfraBoardSeq))
                .code(null)
                .message(null)
                .error(null)
                .build();
    }

    /**
    * Board 등록
    * @param platfraBoardDto
    * @return platfraBoardResDto
    * */
    @PostMapping
    public BaseResponse create(@RequestBody PlatfraBoardDto platfraBoardDto){
        log.info("Create board start.");
        log.info("Request : {}",platfraBoardDto);
        return BaseResponse.builder()
                .data(platfraBoardService.create(platfraBoardDto))
                .code(null)
                .message(null)
                .error(null)
                .build();
    }

    /**
    * Board 수정
    * @param platfraBoardDto
    * @return platfraBoardResDto
    * */
    @PatchMapping
    public BaseResponse update(@RequestBody PlatfraBoardDto platfraBoardDto){
        log.info("Update board start.");
        log.info("Request : {}",platfraBoardDto);
        return BaseResponse.builder()
                .data(platfraBoardService.update(platfraBoardDto))
                .code(null)
                .message(null)
                .error(null)
                .build();
    }

    /**
    * Board 삭제
    * @param
    * @return
    * */
    @DeleteMapping
    public BaseResponse delete(@RequestBody PlatfraBoardDto platfraBoardDto){
        log.info("Delete board start.");
        log.info("Request : {}",platfraBoardDto);
        return BaseResponse.builder()
                .data(platfraBoardService.delete(platfraBoardDto))
                .code(null)
                .message(null)
                .error(null)
                .build();

    }
}
