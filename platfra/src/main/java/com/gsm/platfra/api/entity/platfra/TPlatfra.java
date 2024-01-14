package com.gsm.platfra.api.entity.platfra;

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
@Table(name = "T_PLATFRA")
public class TPlatfra {
    @Id
    @Size(max = 64)
    @Column(name = "PLATFRA_ID", nullable = false, length = 64)
    private String platfraId;
    
    @Size(max = 64)
    @NotNull
    @Column(name = "SUBJECT", nullable = false, length = 64)
    private String subject;
    
    @Size(max = 512)
    @NotNull
    @Column(name = "DESCRIPTION", nullable = false, length = 512)
    private String description;
    
    @Size(max = 1024)
    @NotNull
    @Column(name = "INTRODUCTION", nullable = false, length = 1024)
    private String introduction;
    
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