package com.gsm.platfra.api.services.board.rest;

import com.gsm.platfra.api.data.base.BaseResponse;
import com.gsm.platfra.api.data.platfraboard.PlatfraBoardContentDto;
import com.gsm.platfra.api.services.board.service.PlatfraBoardContentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/platfra/board/content")
public class PlatfraBoardContentController {
    private final PlatfraBoardContentService platfraBoardContentService;

    /**
     * 컨텐츠 검색
     *
     * @param boardContentDto
     * @return List<PlatfraBoardContentDto>
     */
    @GetMapping
    public BaseResponse getList(@ModelAttribute @Valid PlatfraBoardContentDto boardContentDto) {
        log.info("GetBoardContentList start.");
        log.info("Request : {}", boardContentDto);
        return BaseResponse.builder()
            .data(platfraBoardContentService.getList(boardContentDto))
            .code(null)
            .message(null)
            .error(null)
            .build();
    }
    /**
     * 컨텐츠 검색
     *
     * @param platfraBoardContentSeq
     * @return List<PlatfraBoardContentDto>
     */
    @GetMapping("/{platfraBoardContentSeq}")
    public BaseResponse get(@PathVariable Long platfraBoardContentSeq) {
        log.info("GetBoardContentList start.");
        log.info("Request : {}", platfraBoardContentSeq);
        return BaseResponse.builder()
            .data(platfraBoardContentService.get(platfraBoardContentSeq))
            .code(null)
            .message(null)
            .error(null)
            .build();
    }
    /**
     * 컨텐츠 생성
     *
     * @param platfraBoardContentDto
     * @return PlatfraBoardContentResDto
     */
    @PostMapping
    public BaseResponse create(@RequestBody PlatfraBoardContentDto platfraBoardContentDto) {
        log.info("Create Board Conent start.");
        log.info("Request : {}", platfraBoardContentDto);
        return BaseResponse.builder()
            .data(platfraBoardContentService.create(platfraBoardContentDto))
            .code(null)
            .message(null)
            .error(null)
            .build();
    }

    /**
     * 컨텐츠 수정
     *
     * @param platfraBoardContentDto
     * @return PlatfraBoardContentResDto
     */
    @PatchMapping
    public BaseResponse update(@RequestBody PlatfraBoardContentDto platfraBoardContentDto) {
        log.info("Update Board Content start.");
        log.info("Request : {}", platfraBoardContentDto);
        return BaseResponse.builder()
            .data(platfraBoardContentService.update(platfraBoardContentDto))
            .code(null)
            .message(null)
            .error(null)
            .build();
    }

    /**
     * 컨텐츠 삭제
     *
     * @param
     * @return
     */
    @DeleteMapping
    public BaseResponse delete(@RequestBody PlatfraBoardContentDto platfraBoardContentDto) {
        log.info("Delete Board Content start.");
        log.info("Request : {}", platfraBoardContentDto);
        return BaseResponse.builder()
            .data(platfraBoardContentService.delete(platfraBoardContentDto))
            .code(null)
            .message(null)
            .error(null)
            .build();
    }

}
