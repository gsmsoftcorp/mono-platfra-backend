package com.gsm.platfra.api.data.common.error;


import com.gsm.platfra.api.data.common.code.CommonCodeDto;
import com.gsm.platfra.api.data.common.file.CommonFileDto;
import com.gsm.platfra.api.data.common.file.TCommonFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonErrorDto {
    private Long errorSeq;
    private String url;
    private String location;
    private String reqData;
    private String errorMsg;
    private String resData;

    public static TCommonError toEntity(CommonErrorDto dto) {
        return TCommonError.builder()
                .url(dto.url)
                .location(dto.location)
                .reqData(dto.reqData)
                .errorMsg(dto.errorMsg)
                .resData(dto.resData)
                .build();
    }
}
