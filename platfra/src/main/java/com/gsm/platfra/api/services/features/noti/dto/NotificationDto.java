package com.gsm.platfra.api.services.features.noti.dto;

import com.gsm.platfra.api.entity.feature.TFeatureNotification;

public record NotificationDto(
        Long featureNotificationSeq,
        String contentsCd,
        Long contentsSeq,
        String notiTargetUserId,
        String notiDescription,
        String notiUrl,
        Boolean readYn
) {

    public static TFeatureNotification toEntity(NotificationDto dto) {
        return TFeatureNotification.builder()
                .featureNotificationSeq(dto.featureNotificationSeq())
                .contentsCd(dto.contentsCd())
                .contentsSeq(dto.contentsSeq())
                .notiTargetUserId(dto.notiTargetUserId())
                .notiDescription(dto.notiDescription())
                .notiUrl(dto.notiUrl())
                .readYn(dto.readYn())
                .build();
    }
}
