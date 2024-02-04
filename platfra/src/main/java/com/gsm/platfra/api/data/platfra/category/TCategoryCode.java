package com.gsm.platfra.api.data.platfra.category;

import com.gsm.platfra.api.data.base.BaseEntity;
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
@Table(name = "T_CATEGORY_CODE")
public class TCategoryCode extends BaseEntity {
    @Id
    @Size(max = 16)
    @Column(name = "CATEGORY_CD", nullable = false, length = 16)
    private String categoryCd;
    
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