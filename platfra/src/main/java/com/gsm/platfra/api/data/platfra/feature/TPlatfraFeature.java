package com.gsm.platfra.api.data.platfra.feature;

import com.gsm.platfra.api.data.base.BaseEntity;
import com.gsm.platfra.api.data.platfra.TPlatfra;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "T_PLATFRA_FEATURE")
public class TPlatfraFeature extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLATFRA_FEATURE_SEQ", nullable = false)
    private Long id;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PLATFRA_ID", nullable = false)
    private TPlatfra platfra;
    
    @Size(max = 32)
    @NotNull
    @Column(name = "FEATURE_CD", nullable = false, length = 32)
    private String featureCd;
    
    @NotNull
    @Column(name = "USE_YN", nullable = false, length = 1)
    private Boolean useYn;
    
}