package com.gsm.platfra.api.services.board.mapper;

import com.gsm.platfra.api.dto.platfraboard.PlatfraBoardContentDto;
import com.gsm.platfra.api.entity.platfraboard.TPlatfraBoardContent;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PlatfraBoardContentMapper {
  TPlatfraBoardContent dtoToEntity(PlatfraBoardContentDto dto);
  PlatfraBoardContentDto entityToDto(TPlatfraBoardContent entity);
  List<TPlatfraBoardContent> dtoListToEntityList (List<PlatfraBoardContentDto> dtoList);
  List<PlatfraBoardContentDto> entityListToDtoList(List<TPlatfraBoardContent> entityList);

}
