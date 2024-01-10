package com.gsm.platfra.api.entity.feature;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "T_FEATURE_VIEW")
public class TFeatureView {
    @Id
    @Column(name = "FEATURE_VIEW_SEQ", nullable = false)
    private Long id;
    
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
    
    @NotNull
    @Column(name = "DEL_YN", nullable = false)
    private Character delYn;
    
    @Size(max = 64)
    @NotNull
    @Column(name = "REG_USER_ID", nullable = false, length = 64)
    private String regUserId;
    
    @NotNull
    @Column(name = "REG_DATE", nullable = false)
    private Instant regDate;
    
    @Size(max = 64)
    @NotNull
    @Column(name = "MOD_USER_ID", nullable = false, length = 64)
    private String modUserId;
    
    @NotNull
    @Column(name = "MOD_DATE", nullable = false)
    private Instant modDate;
    
}