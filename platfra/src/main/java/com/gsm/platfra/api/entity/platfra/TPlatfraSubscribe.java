package com.gsm.platfra.api.entity.platfra;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "T_PLATFRA_SUBSCRIBE")
public class TPlatfraSubscribe {
    
    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PLATFRA_ID", nullable = false)
    private TPlatfra platfra;
    
    //TODO [JPA Buddy] generate columns from DB
}