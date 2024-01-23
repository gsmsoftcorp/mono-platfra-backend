package com.gsm.platfra.api.services.board.mapper;

import com.gsm.platfra.api.entity.platfraboard.TPlatfraBoard;
import com.gsm.platfra.api.services.board.dto.table.PlatfraBoardDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface PlatfraBoardMapper {
    public List<PlatfraBoardDto> boardListToDtoList (List<TPlatfraBoard> boardList);

    public List<TPlatfraBoard> boardDtoListtoEntityList(List<PlatfraBoardDto> boardDtoList);

    public PlatfraBoardDto boardToDto (TPlatfraBoard board);
    public TPlatfraBoard dtoToBoard (PlatfraBoardDto boardDto);
}

