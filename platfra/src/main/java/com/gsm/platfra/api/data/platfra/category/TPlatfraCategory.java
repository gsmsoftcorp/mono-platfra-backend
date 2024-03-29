package com.gsm.platfra.api.data.platfra.category;

import com.gsm.platfra.api.data.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "T_PLATFRA_CATEGORY")
public class TPlatfraCategory extends BaseEntity {
    @EmbeddedId
    private TPlatfraCategoryId id;
    
//    @MapsId("platfraId")
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "PLATFRA_ID", nullable = false)
//    private TPlatfra platfra;
    
}