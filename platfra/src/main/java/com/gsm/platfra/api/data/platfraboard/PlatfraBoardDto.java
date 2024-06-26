package com.gsm.platfra.api.data.platfraboard;

import com.gsm.platfra.api.data.platfra.PlatfraDto;
import com.gsm.platfra.api.data.platfra.TPlatfra;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlatfraBoardDto {

    private Long platfraBoardSeq;
    private PlatfraDto platfraDto;
    private String platfraId;
    private String subject;
    private String description;
    private List<PlatfraBoardContentDto> platfraBoardContentDtoList;
}
