package com.gsm.platfra.api.data.platfraboard;

import com.gsm.platfra.api.data.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "T_PLATFRA_BOARD_FEATURE")
public class TPlatfraBoardFeature extends BaseEntity {
    @Id
    @Column(name = "PLATFRA_BOARD_FEATURE_SEQ", nullable = false)
    private Long id;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PLATFRA_BOARD_SEQ", nullable = false)
    private TPlatfraBoard platfraBoardSeq;
    
    @Size(max = 32)
    @NotNull
    @Column(name = "FEATURE_CD", nullable = false, length = 32)
    private String featureCd;
    
    @NotNull
    @Column(name = "USE_YN", nullable = false, length = 1)
    private Boolean useYn;
    
}