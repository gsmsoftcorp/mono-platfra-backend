package com.gsm.platfra.api.services.board.service;

import com.gsm.platfra.api.data.platfra.TPlatfra;
import com.gsm.platfra.api.data.platfra.TPlatfraRepository;
import com.gsm.platfra.api.data.platfraboard.PlatfraBoardDto;
import com.gsm.platfra.api.data.platfraboard.TPlatfraBoard;
import com.gsm.platfra.api.data.platfraboard.TPlatfraBoardRepository;
import com.gsm.platfra.api.services.board.dto.PlatfraBoardResDto;
import com.gsm.platfra.api.services.board.mapper.PlatfraBoardMapper;
import com.gsm.platfra.api.services.board.query.PlatfraBoardQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlatfraBoardService {
    private final PlatfraBoardQueryRepository platfraBoardQueryRepository;
    private final TPlatfraBoardRepository tPlatfraBoardRepository;
    private final TPlatfraRepository tPlatfraRepository;
    private PlatfraBoardMapper platfraBoardMapper = Mappers.getMapper(PlatfraBoardMapper.class);

    public List<PlatfraBoardDto> getList(PlatfraBoardDto platfraBoardDto){
        return platfraBoardQueryRepository.getList(platfraBoardDto);
    }

    public PlatfraBoardResDto getPlatfraBoardDetail(Long platfraBoardSeq){
        TPlatfraBoard platfraBoard = tPlatfraBoardRepository.findByPlatfraBoardSeq(platfraBoardSeq);
        PlatfraBoardDto platfraBoardDto = platfraBoardMapper.boardToDto(platfraBoard);
        PlatfraBoardResDto platfraBoardResDto = PlatfraBoardResDto.builder()
            .platfraBoardDto(platfraBoardDto)
            .build();
        return platfraBoardResDto;
    }

    @Transactional
    public PlatfraBoardResDto create(PlatfraBoardDto platfraBoardDto){

        TPlatfra platfra = tPlatfraRepository.findByPlatfraId(platfraBoardDto.getPlatfraId());

        TPlatfraBoard tPlatfraBoard = TPlatfraBoard.builder()
            .tPlatfra(platfra)
            .platfraId(platfraBoardDto.getPlatfraId())
            .description(platfraBoardDto.getDescription())
            .subject(platfraBoardDto.getSubject())
            .build();

        TPlatfraBoard savedBoard = tPlatfraBoardRepository.saveAndFlush(tPlatfraBoard);

        return PlatfraBoardResDto.builder()
            .platfraBoardDto(platfraBoardMapper.boardToDto(savedBoard))
            .build();
    }

    @Transactional
    public PlatfraBoardResDto update (PlatfraBoardDto platfraBoardDto){
        TPlatfraBoard platfraBoard = tPlatfraBoardRepository.findByPlatfraBoardSeq(platfraBoardDto.getPlatfraBoardSeq());
        platfraBoard.update(platfraBoardDto);
        tPlatfraBoardRepository.flush();

        PlatfraBoardDto responseDto = platfraBoardMapper.boardToDto(platfraBoard);
        return PlatfraBoardResDto.builder().platfraBoardDto(responseDto).build();
    }

    @Transactional
    public boolean delete (PlatfraBoardDto platfraBoardDto){
        return platfraBoardQueryRepository.delete(platfraBoardDto.getPlatfraBoardSeq()) > 0;
    }

}
