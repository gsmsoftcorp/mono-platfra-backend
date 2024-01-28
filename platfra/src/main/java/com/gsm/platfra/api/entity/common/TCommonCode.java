package com.gsm.platfra.api.entity.common;

import com.gsm.platfra.api.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "T_COMMON_CODE")
public class TCommonCode extends BaseEntity {
    @Id
    @Size(max = 16)
    @Column(name = "COMMON_CD", nullable = false, length = 16)
    private String commonCd;
    
    @Size(max = 16)
    @Column(name = "PARENT_CD", length = 16)
    private String parentCd;
    
    @Size(max = 16)
    @Column(name = "NAME", length = 16)
    private String name;
    
    @Size(max = 256)
    @Column(name = "DESCRIPTION", length = 256)
    private String description;
    
}