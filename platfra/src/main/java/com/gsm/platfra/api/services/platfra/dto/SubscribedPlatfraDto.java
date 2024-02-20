package com.gsm.platfra.api.services.platfra.dto;

import com.gsm.platfra.api.data.account.TAccountDto;
import com.gsm.platfra.api.data.platfra.PlatfraDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscribedPlatfraDto {

    TAccountDto userInfo;
    List<PlatfraDto> subscribedPlatfra;

    public static SubscribedPlatfraDto of(TAccountDto userInfo, List<PlatfraDto> subscribedPlatfra) {
        return new SubscribedPlatfraDto(userInfo, subscribedPlatfra);
    }

}
