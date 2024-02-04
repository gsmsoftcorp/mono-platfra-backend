package com.gsm.platfra.api.services.board.dto;

import com.gsm.platfra.api.data.platfraboard.PlatfraBoardContentDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlatfraBoardContentResDto {
  public PlatfraBoardContentDto platfraBoardContentDto;
}
