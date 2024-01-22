package com.gsm.platfra.api.services.board.service;

import com.gsm.platfra.api.services.board.dto.table.PlatfraBoardDto;
import com.gsm.platfra.api.services.board.repository.PlatfraBoardRepository;
import com.gsm.platfra.api.services.board.repository.query.PlatfraBoardQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class PlatfraBoardService {
    private final PlatfraBoardQueryRepository platfraBoardQueryRepository;
    private final PlatfraBoardRepository platfraBoardRepository;

    public List<PlatfraBoardDto> getList(PlatfraBoardDto platfraBoardDto){
        log.info("getList : ",platfraBoardQueryRepository.getList(platfraBoardDto));
        return platfraBoardQueryRepository.getList(platfraBoardDto);
    }
}
