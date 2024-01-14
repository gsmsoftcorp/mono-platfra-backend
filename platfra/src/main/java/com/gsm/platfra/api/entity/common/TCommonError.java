package com.gsm.platfra.api.entity.common;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "T_COMMON_ERROR")
public class TCommonError {
    @Id
    @Column(name = "ERROR_SEQ", nullable = false)
    private Long id;
    
    //TODO [JPA Buddy] generate columns from DB
}