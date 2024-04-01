package com.gsm.platfra.api.data.feature.view;

import com.gsm.platfra.api.data.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
@Table(name = "T_FEATURE_VIEW")
public class TFeatureView extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FEATURE_VIEW_SEQ", nullable = false)
    private Long featureViewSeq;
    
    @Size(max = 16)
    @NotNull
    @Column(name = "CONTENTS_CD", nullable = false, length = 16)
    private String contentsCd;
    
    @NotNull
    @Column(name = "CONTENTS_SEQ", nullable = false)
    private Long contentsSeq;
    
    @Size(max = 64)
    @NotNull
    @Column(name = "USER_ID", nullable = false, length = 64)
    private String userId;

    @Size(max = 15)
    @NotNull
    @Column(name = "ADDRESS", nullable = false, length = 15)
    private String address;

}