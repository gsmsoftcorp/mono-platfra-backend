package com.gsm.platfra.api.entity.platfra.category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "T_CATEGORY_CODE")
public class TCategoryCode {
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
    
    @NotNull
    @Column(name = "DEL_YN", nullable = false)
    private Character delYn;
    
    @Size(max = 64)
    @NotNull
    @Column(name = "REG_USER_ID", nullable = false, length = 64)
    private String regUserId;
    
    @NotNull
    @Column(name = "REG_DATE", nullable = false)
    private Instant regDate;
    
    @Size(max = 64)
    @NotNull
    @Column(name = "MOD_USER_ID", nullable = false, length = 64)
    private String modUserId;
    
    @NotNull
    @Column(name = "MOD_DATE", nullable = false)
    private Instant modDate;
    
}