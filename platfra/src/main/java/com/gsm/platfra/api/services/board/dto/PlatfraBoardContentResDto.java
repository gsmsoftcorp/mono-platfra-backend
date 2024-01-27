package com.gsm.platfra.api.services.board.dto;

import com.gsm.platfra.api.dto.platfraboard.PlatfraBoardContentDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlatfraBoardContentResDto {
  public PlatfraBoardContentDto platfraBoardContentDto;
}
