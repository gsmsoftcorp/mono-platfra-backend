package com.gsm.platfra.api.data.feature.comment;

import com.gsm.platfra.api.data.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "T_FEATURE_COMMENT")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TFeatureComment extends BaseEntity {
    @Id
    @Column(name = "FEATURE_COMMENT_SEQ", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long featureCommentSeq;
    
    @Column(name = "PARENT_SEQ")
    private Long parentSeq;
    
    @Size(max = 16)
    @NotNull
    @Column(name = "CONTENTS_CD", nullable = false)
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

    public void update(FeatureCommentDto featureCommentDto) {
        this.comment = featureCommentDto.getComment();
    }
}