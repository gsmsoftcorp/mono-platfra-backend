package com.gsm.platfra.api.services.board.service;

import com.gsm.platfra.api.dto.platfraboard.PlatfraBoardContentDto;
import com.gsm.platfra.api.dto.platfraboard.PlatfraBoardDto;
import com.gsm.platfra.api.entity.platfraboard.TPlatfraBoard;
import com.gsm.platfra.api.entity.platfraboard.TPlatfraBoardContent;
import com.gsm.platfra.api.services.board.dto.PlatfraBoardContentResDto;
import com.gsm.platfra.api.services.board.mapper.PlatfraBoardContentMapper;
import com.gsm.platfra.api.services.board.mapper.PlatfraBoardMapper;
import com.gsm.platfra.api.services.board.repository.PlatfraBoardContentRepository;
import com.gsm.platfra.api.services.board.repository.PlatfraBoardRepository;
import com.gsm.platfra.api.services.board.repository.query.PlatfraBoardContentQueryRepository;
import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlatfraBoardContentService {

  private final PlatfraBoardContentRepository boardContentRepository;
  private final PlatfraBoardContentQueryRepository boardContentQueryRepository;
  private final PlatfraBoardRepository platfraBoardRepository;
  private PlatfraBoardContentMapper boardContentMapper = Mappers.getMapper(PlatfraBoardContentMapper.class);

  public List<PlatfraBoardContentDto> getList(PlatfraBoardContentDto platfraBoardContentDto){
    return boardContentQueryRepository.getList(platfraBoardContentDto);
  }

  @Transactional
  public PlatfraBoardContentResDto create(PlatfraBoardContentDto boardContentDto){
    boardContentDto.setRegDate(Instant.now());
    TPlatfraBoard tPlatfraBoard = platfraBoardRepository.findByPlatfraBoardSeq(boardContentDto.getPlatfraBoardSeq());
    TPlatfraBoardContent tPlatfraBoardContent = TPlatfraBoardContent.builder()
        .platfraBoardSeq(boardContentDto.getPlatfraBoardSeq())
        .tPlatfraBoard(tPlatfraBoard)
        .contentSeq(boardContentDto.getContentSeq())
        .contentNo(boardContentDto.getContentNo())
        .content(boardContentDto.getContent())
        .title(boardContentDto.getTitle())
        .regUserId(boardContentDto.getRegUserId())
        .modUserId(boardContentDto.getRegUserId())
        .regDate(boardContentDto.getRegDate())
        .modDate(boardContentDto.getRegDate())
        .delYn(Boolean.FALSE)
        .build();

    PlatfraBoardContentDto platfraBoardContentDto = boardContentMapper.entityToDto(boardContentRepository.save(tPlatfraBoardContent));
    return PlatfraBoardContentResDto.builder().platfraBoardContentDto(platfraBoardContentDto).build();
  }

  @Transactional
  public PlatfraBoardContentResDto update(PlatfraBoardContentDto boardContentDto){
    TPlatfraBoardContent tPlatfraBoardContent = boardContentRepository.findById(boardContentDto.getContentSeq()).orElseThrow();
    tPlatfraBoardContent.update(boardContentDto);
    boardContentRepository.flush();

    PlatfraBoardContentDto platfraBoardContentDto = boardContentMapper.entityToDto(tPlatfraBoardContent);
    return PlatfraBoardContentResDto.builder().platfraBoardContentDto(platfraBoardContentDto).build();
  }

  @Transactional
  public boolean delete(PlatfraBoardContentDto platfraBoardContentDto){
    return boardContentQueryRepository.delete(platfraBoardContentDto.getContentSeq()) > 0;
  }


}
