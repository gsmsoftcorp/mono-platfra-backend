package com.gsm.platfra.api.services.platfra.dto;

import com.gsm.platfra.api.data.platfra.PlatfraContentDto;
import com.gsm.platfra.api.data.platfraboard.PlatfraBoardContentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentDto {
    List<PlatfraContentDto> contents;
    List<PlatfraBoardContentDto> boardContents;

    public static ContentDto of(List<PlatfraContentDto> myContents, List<PlatfraBoardContentDto> myBoardContent) {
        return new ContentDto(myContents, myBoardContent);
    }
}
