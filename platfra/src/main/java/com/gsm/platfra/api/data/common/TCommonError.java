package com.gsm.platfra.api.data.common;

import com.gsm.platfra.api.data.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "T_COMMON_ERROR")
public class TCommonError extends BaseEntity {
    @Id
    @Column(name = "ERROR_SEQ", nullable = false)
    private Long id;
    
    //TODO [JPA Buddy] generate columns from DB
}