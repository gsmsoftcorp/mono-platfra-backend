package com.gsm.platfra.api.data.feature.like;

import com.gsm.platfra.api.data.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "T_FEATURE_LIKE")
public class TFeatureLike extends BaseEntity {
    @Id
    @Column(name = "FEATURE_LIKE_SEQ", nullable = false)
    private Long featureLikeSeq;
    
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
    @Column(name = "LIKE_YN", nullable = false, length = 1)
    private Boolean likeYn;
    
}