package com.gsm.platfra.api.services.features.noti.dto;

import com.gsm.platfra.api.entity.feature.TFeatureNotification;

public record NotificationDto(
        Long featureNotiSeq,
        String contentsCd,
        Long contentsSeq,
        Long featureSeq,
        String notiTargetUserId,
        String notiTitle,
        String notiDescription,
        String notiUrl
) {

    public static TFeatureNotification toEntity(NotificationDto dto) {
        return TFeatureNotification.builder()
                .featureNotiSeq(dto.featureNotiSeq())
                .contentsCd(dto.contentsCd())
                .contentsSeq(dto.contentsSeq())
                .featureSeq(dto.featureSeq())
                .notiTargetUserId(dto.notiTargetUserId())
                .notiTitle(dto.notiTitle())
                .notiDescription(dto.notiDescription())
                .notiUrl(dto.notiUrl())
                .readYn(false)
                .sendYn(false)
                .build();
    }
}
