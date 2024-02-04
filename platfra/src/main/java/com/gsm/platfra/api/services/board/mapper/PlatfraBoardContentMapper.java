package com.gsm.platfra.api.services.board.mapper;

import com.gsm.platfra.api.data.platfraboard.PlatfraBoardContentDto;
import com.gsm.platfra.api.data.platfraboard.TPlatfraBoardContent;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PlatfraBoardContentMapper {
  TPlatfraBoardContent dtoToEntity(PlatfraBoardContentDto dto);
  PlatfraBoardContentDto entityToDto(TPlatfraBoardContent entity);
  List<TPlatfraBoardContent> dtoListToEntityList (List<PlatfraBoardContentDto> dtoList);
  List<PlatfraBoardContentDto> entityListToDtoList(List<TPlatfraBoardContent> entityList);

}
