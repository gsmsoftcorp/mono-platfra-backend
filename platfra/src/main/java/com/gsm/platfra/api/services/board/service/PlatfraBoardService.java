package com.gsm.platfra.api.services.board.service;

import com.gsm.platfra.api.entity.platfraboard.TPlatfraBoard;
import com.gsm.platfra.api.dto.platfraboard.PlatfraBoardDto;
import com.gsm.platfra.api.services.board.mapper.PlatfraBoardMapper;
import com.gsm.platfra.api.services.board.repository.PlatfraBoardRepository;
import com.gsm.platfra.api.services.board.repository.query.PlatfraBoardQueryRepository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class PlatfraBoardService {
    private final PlatfraBoardQueryRepository platfraBoardQueryRepository;
    private final PlatfraBoardRepository platfraBoardRepository;
    private final PlatfraBoardMapper platfraBoardMapper;

    public List<PlatfraBoardDto> getList(PlatfraBoardDto platfraBoardDto){
        log.info("getList : ",platfraBoardQueryRepository.getList(platfraBoardDto));
        return platfraBoardQueryRepository.getList(platfraBoardDto);
    }

    public List<PlatfraBoardDto> getPlatfraBoardList(String platfraId){
        List<TPlatfraBoard> platfraBoardList = platfraBoardRepository.findAllByPlatfraId(platfraId);
        List<PlatfraBoardDto> platfraBoardDtoList = platfraBoardMapper.boardListToDtoList(platfraBoardList);
        return platfraBoardDtoList;
    }

    public PlatfraBoardDto getPlatfraBoardDetail(Long platfraBoardSeq){
        TPlatfraBoard platfraBoard = platfraBoardRepository.findByPlatfraBoardSeq(platfraBoardSeq);
        return platfraBoardMapper.boardToDto(platfraBoard);
    }
}
