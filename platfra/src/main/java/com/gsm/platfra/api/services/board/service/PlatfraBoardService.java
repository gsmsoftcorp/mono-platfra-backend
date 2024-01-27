package com.gsm.platfra.api.services.board.service;

import com.gsm.platfra.api.dto.platfraboard.PlatfraBoardDto;
import com.gsm.platfra.api.entity.platfra.TPlatfra;
import com.gsm.platfra.api.entity.platfraboard.TPlatfraBoard;
import com.gsm.platfra.api.services.board.dto.PlatfraBoardResDto;
import com.gsm.platfra.api.services.board.mapper.PlatfraBoardMapper;
import com.gsm.platfra.api.services.board.repository.PlatfraBoardRepository;
import com.gsm.platfra.api.services.board.repository.query.PlatfraBoardQueryRepository;
import com.gsm.platfra.api.services.platfra.repository.TPlatfraRepository;
import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class PlatfraBoardService {
    private final PlatfraBoardQueryRepository platfraBoardQueryRepository;
    private final PlatfraBoardRepository platfraBoardRepository;
    private final TPlatfraRepository tPlatfraRepository;
    private PlatfraBoardMapper platfraBoardMapper = Mappers.getMapper(PlatfraBoardMapper.class);

    public List<PlatfraBoardDto> getList(PlatfraBoardDto platfraBoardDto){
        return platfraBoardQueryRepository.getList(platfraBoardDto);
    }

    public PlatfraBoardResDto getPlatfraBoardDetail(Long platfraBoardSeq){
        TPlatfraBoard platfraBoard = platfraBoardRepository.findByPlatfraBoardSeq(platfraBoardSeq);
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
            .modUserId(platfraBoardDto.getModUserId())
            .regUserId(platfraBoardDto.getRegUserId())
            .regDate(Instant.now())
            .modDate(Instant.now())
            .delYn(Boolean.FALSE)
            .build();

        TPlatfraBoard savedBoard = platfraBoardRepository.saveAndFlush(tPlatfraBoard);

        return PlatfraBoardResDto.builder()
            .platfraBoardDto(platfraBoardMapper.boardToDto(savedBoard))
            .build();
    }

    @Transactional
    public PlatfraBoardResDto update (PlatfraBoardDto platfraBoardDto){
        TPlatfraBoard platfraBoard = platfraBoardRepository.findByPlatfraBoardSeq(platfraBoardDto.getPlatfraBoardSeq());
        platfraBoard.update(platfraBoardDto);
        platfraBoardRepository.flush();

        PlatfraBoardDto responseDto = platfraBoardMapper.boardToDto(platfraBoard);
        return PlatfraBoardResDto.builder().platfraBoardDto(responseDto).build();
    }

    @Transactional
    public boolean delete (PlatfraBoardDto platfraBoardDto){
        return platfraBoardQueryRepository.delete(platfraBoardDto.getPlatfraBoardSeq()) > 0;
    }

}
