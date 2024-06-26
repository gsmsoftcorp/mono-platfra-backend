package com.gsm.platfra.api.services.board.service;

import com.gsm.platfra.api.data.platfraboard.*;
import com.gsm.platfra.api.services.board.dto.PlatfraBoardContentResDto;
import com.gsm.platfra.api.services.board.mapper.PlatfraBoardContentMapper;
import com.gsm.platfra.api.services.board.query.PlatfraBoardContentQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PlatfraBoardContentService {
    private final TPlatfraBoardContentRepository tPlatfraBoardContentRepository;
    private final PlatfraBoardContentQueryRepository platfraBoardContentQueryRepository;
    private final TPlatfraBoardRepository tPlatfraBoardRepository;
    private final PlatfraBoardContentMapper platfraBoardContentMapper = Mappers.getMapper(PlatfraBoardContentMapper.class);

    public List<PlatfraBoardContentDto> getList(PlatfraBoardContentDto platfraBoardContentDto) {
        List<PlatfraBoardContentDto> platfraBoardContentDtoList = platfraBoardContentQueryRepository.getList(platfraBoardContentDto);
        return platfraBoardContentDtoList;
    }
    public PlatfraBoardContentDto get(Long platfraBoardContentSeq) {
        TPlatfraBoardContent tPlatfraBoardContent = tPlatfraBoardContentRepository.findById(platfraBoardContentSeq).orElseThrow();
        return platfraBoardContentMapper.entityToDto(tPlatfraBoardContent);
    }

    @Transactional
    public PlatfraBoardContentResDto create(PlatfraBoardContentDto boardContentDto) {

        TPlatfraBoard tPlatfraBoard = tPlatfraBoardRepository.findById(boardContentDto.getPlatfraBoardSeq())
            .orElseThrow(() -> new IllegalArgumentException("해당 플랫폼이 존재하지 않습니다."));

        TPlatfraBoardContent tPlatfraBoardContent = TPlatfraBoardContent.builder()
            .platfraBoardSeq(boardContentDto.getPlatfraBoardSeq())
            .tPlatfraBoard(tPlatfraBoard)
            .contentSeq(boardContentDto.getContentSeq())
            .contentNo(boardContentDto.getContentNo())
            .title(boardContentDto.getTitle())
            .content(boardContentDto.getContent())
            .build();

        PlatfraBoardContentDto platfraBoardContentDto = platfraBoardContentMapper.entityToDto(tPlatfraBoardContentRepository.save(tPlatfraBoardContent));
        return PlatfraBoardContentResDto.builder().platfraBoardContentDto(platfraBoardContentDto).build();
    }

    @Transactional
    public PlatfraBoardContentResDto update(PlatfraBoardContentDto boardContentDto) {

        TPlatfraBoardContent tPlatfraBoardContent = tPlatfraBoardContentRepository.findById(boardContentDto.getContentSeq())
            .orElseThrow(() -> new IllegalArgumentException("해당 플랫폼이 존재하지 않습니다."));

        tPlatfraBoardContent.update(boardContentDto);
        TPlatfraBoardContent savedTPlatfraBoardContent = tPlatfraBoardContentRepository.saveAndFlush(tPlatfraBoardContent);

        PlatfraBoardContentDto platfraBoardContentDto = platfraBoardContentMapper.entityToDto(savedTPlatfraBoardContent);
        return PlatfraBoardContentResDto.builder().platfraBoardContentDto(platfraBoardContentDto).build();
    }

    @Transactional
    public boolean delete(PlatfraBoardContentDto platfraBoardContentDto) {

        tPlatfraBoardContentRepository.findById(platfraBoardContentDto.getContentSeq())
            .orElseThrow(() -> new IllegalArgumentException("해당 플랫폼이 존재하지 않습니다."));

        return platfraBoardContentQueryRepository.delete(platfraBoardContentDto.getContentSeq()) > 0;
    }



}
