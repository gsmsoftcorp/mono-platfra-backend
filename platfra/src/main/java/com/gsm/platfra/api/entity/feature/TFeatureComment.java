package com.gsm.platfra.api.entity.feature;

import com.gsm.platfra.api.entity.base.BaseEntity;
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
@Table(name = "T_FEATURE_COMMENT")
public class TFeatureComment extends BaseEntity {
    @Id
    @Column(name = "FEATURE_COMMENT_SEQ", nullable = false)
    private Long id;
    
    @Column(name = "PARENT_SEQ")
    private Long parentSeq;
    
    @Size(max = 16)
    @NotNull
    @Column(name = "CONTENTS_CD", nullable = false, length = 16)
    private String contentsCd;
    
    @NotNull
    @Column(name = "CONTENTS_SEQ", nullable = false)
    private Long contentsSeq;
    
    @Size(max = 4000)
    @NotNull
    @Column(name = "COMMENT", nullable = false, length = 4000)
    private String comment;
    
    @Size(max = 64)
    @NotNull
    @Column(name = "USER_ID", nullable = false, length = 64)
    private String userId;
    
}