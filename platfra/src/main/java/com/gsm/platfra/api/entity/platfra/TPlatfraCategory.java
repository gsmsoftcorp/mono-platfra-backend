package com.gsm.platfra.api.entity.platfra;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "T_PLATFRA_CATEGORY")
public class TPlatfraCategory {
    @EmbeddedId
    private TPlatfraCategoryId id;
    
    @MapsId("platfraId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PLATFRA_ID", nullable = false)
    private TPlatfra platfra;
    
}