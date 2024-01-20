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
@Table(name = "T_PLATFRA_BOARD_CONTENT")
public class TPlatfraBoardContent {
    @Id
    @Column(name = "CONTENT_SEQ", nullable = false)
    private Long contentSeq;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PLATFRA_BOARD_SEQ", referencedColumnName = "PLATFRA_BOARD_SEQ", nullable = false)
    private TPlatfraBoard tPlatfraBoard;
    
    @Size(max = 64)
    @NotNull
    @Column(name = "PLATFRA_BOARD_SEQ", nullable = false, length = 64, insertable = false, updatable = false)
    private Long platfraBoardSeq;
    
    @NotNull
    @Column(name = "CONTENT_NO", nullable = false)
    private Long contentNo;
    
    @Size(max = 256)
    @NotNull
    @Column(name = "TITLE", nullable = false, length = 256)
    private String title;
    
    @NotNull
    @Lob
    @Column(name = "CONTENT", nullable = false)
    private String content;
    
    @NotNull
    @Column(name = "DEL_YN", nullable = false, length = 1)
    private Boolean delYn;
    
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