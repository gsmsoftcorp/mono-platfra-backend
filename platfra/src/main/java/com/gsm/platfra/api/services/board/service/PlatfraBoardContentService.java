package com.gsm.platfra.api.services.board.service;

import com.gsm.platfra.api.data.platfraboard.*;
import com.gsm.platfra.api.services.board.dto.PlatfraBoardContentResDto;
import com.gsm.platfra.api.services.board.mapper.PlatfraBoardContentMapper;
import com.gsm.platfra.api.services.board.query.PlatfraBoardContentQueryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlatfraBoardContentService {
    private final TPlatfraBoardContentRepository boardContentRepository;
    private final PlatfraBoardContentQueryRepository boardContentQueryRepository;
    private final TPlatfraBoardRepository platfraBoardRepository;
    private final PlatfraBoardContentMapper boardContentMapper = Mappers.getMapper(PlatfraBoardContentMapper.class);
    
    public List<PlatfraBoardContentDto> getList(PlatfraBoardContentDto platfraBoardContentDto) {
        return boardContentQueryRepository.getList(platfraBoardContentDto);
    }
    
    @Transactional
    public PlatfraBoardContentResDto create(PlatfraBoardContentDto boardContentDto) {

        TPlatfraBoard tPlatfraBoard = platfraBoardRepository.findByPlatfraBoardSeq(boardContentDto.getPlatfraBoardSeq())
            .orElseThrow(() -> new IllegalArgumentException("해당 플랫폼이 존재하지 않습니다."));

        TPlatfraBoardContent tPlatfraBoardContent = TPlatfraBoardContent.builder()
            .platfraBoardSeq(boardContentDto.getPlatfraBoardSeq())
            .tPlatfraBoard(tPlatfraBoard)
            .contentSeq(boardContentDto.getContentSeq())
            .contentNo(boardContentDto.getContentNo())
            .content(boardContentDto.getContent())
            .title(boardContentDto.getTitle())
            .build();
        
        PlatfraBoardContentDto platfraBoardContentDto = boardContentMapper.entityToDto(boardContentRepository.save(tPlatfraBoardContent));
        return PlatfraBoardContentResDto.builder().platfraBoardContentDto(platfraBoardContentDto).build();
    }
    
    @Transactional
    public PlatfraBoardContentResDto update(PlatfraBoardContentDto boardContentDto) {

        TPlatfraBoardContent tPlatfraBoardContent = boardContentRepository.findById(boardContentDto.getContentSeq())
            .orElseThrow(() -> new IllegalArgumentException("해당 플랫폼이 존재하지 않습니다."));

        tPlatfraBoardContent.update(boardContentDto);
        boardContentRepository.flush();
        
        PlatfraBoardContentDto platfraBoardContentDto = boardContentMapper.entityToDto(tPlatfraBoardContent);
        return PlatfraBoardContentResDto.builder().platfraBoardContentDto(platfraBoardContentDto).build();
    }
    
    @Transactional
    public boolean delete(PlatfraBoardContentDto platfraBoardContentDto) {

        boardContentRepository.findById(platfraBoardContentDto.getContentSeq())
            .orElseThrow(() -> new IllegalArgumentException("해당 플랫폼이 존재하지 않습니다."));

        return boardContentQueryRepository.delete(platfraBoardContentDto.getContentSeq()) > 0;
    }
    
    
}
