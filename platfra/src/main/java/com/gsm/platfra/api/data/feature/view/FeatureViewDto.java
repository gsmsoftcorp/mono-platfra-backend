package com.gsm.platfra.api.data.feature.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeatureViewDto {
    private Long featureViewSeq;
    private String contentsCd;
    private Long contentsSeq;
    private String userId;
    private String address;

    public static TFeatureView toEntity(FeatureViewDto dto) {
        return TFeatureView.builder()
                .featureViewSeq(dto.featureViewSeq)
                .contentsCd(dto.contentsCd)
                .contentsSeq(dto.contentsSeq)
                .userId(dto.userId)
                .address(dto.address)
                .build();
    }

    public static FeatureViewDto of(TFeatureView tFeatureView) {
        return FeatureViewDto.builder()
                .featureViewSeq(tFeatureView.getContentsSeq())
                .contentsCd(tFeatureView.getContentsCd())
                .contentsSeq(tFeatureView.getContentsSeq())
                .userId(tFeatureView.getUserId())
                .address(tFeatureView.getAddress())
                .build();
    }

}
