package com.gsm.platfra.api.data.platfra;

import com.gsm.platfra.api.data.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "T_PLATFRA_SUBSCRIBE")
public class TPlatfraSubscribe extends BaseEntity {
    
    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PLATFRA_ID", nullable = false)
    private TPlatfra platfra;
    
    //TODO [JPA Buddy] generate columns from DB
}