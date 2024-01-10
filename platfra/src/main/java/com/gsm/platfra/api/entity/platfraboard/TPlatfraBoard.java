package com.gsm.platfra.api.entity.platfraboard;

import com.gsm.platfra.api.entity.platfra.TPlatfra;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "T_PLATFRA_BOARD")
public class TPlatfraBoard {
    @Id
    @Column(name = "PLATFRA_BOARD_SEQ", nullable = false)
    private Long id;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PLATFRA_ID", nullable = false)
    private TPlatfra platfra;
    
    @Size(max = 100)
    @NotNull
    @Column(name = "SUBJECT", nullable = false, length = 100)
    private String subject;
    
    @Size(max = 512)
    @NotNull
    @Column(name = "DESCRIPTION", nullable = false, length = 512)
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