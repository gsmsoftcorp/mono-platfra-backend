package com.gsm.platfra.api.services.board.dto;

import com.gsm.platfra.api.dto.platfraboard.PlatfraBoardDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlatfraBoardResDto {
    public PlatfraBoardDto platfraBoardDto;
}
