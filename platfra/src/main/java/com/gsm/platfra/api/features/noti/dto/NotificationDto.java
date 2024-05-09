package com.gsm.platfra.api.features.noti.dto;

import com.gsm.platfra.api.data.feature.noti.TFeatureNotification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDto {
    private Long featureNotiSeq;
    private String contentsCd;
    private Long contentsSeq;
    private Long featureSeq;
    private String notiTargetUserId;
    private String notiTitle;
    private String notiDescription;
    private String notiUrl;

    public static TFeatureNotification toEntity(NotificationDto dto) {
        return TFeatureNotification.builder()
                .featureNotiSeq(dto.featureNotiSeq)
                .contentsCd(dto.contentsCd)
                .contentsSeq(dto.contentsSeq)
                .featureSeq(dto.featureSeq)
                .notiTargetUserId(dto.notiTargetUserId)
                .notiTitle(dto.notiTitle)
                .notiDescription(dto.notiDescription)
                .notiUrl(dto.notiUrl)
                .readYn(false)
                .sendYn(false)
                .build();
    }
}
