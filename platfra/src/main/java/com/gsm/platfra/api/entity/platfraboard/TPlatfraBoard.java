package com.gsm.platfra.api.entity.platfraboard;

import com.gsm.platfra.api.dto.platfraboard.PlatfraBoardDto;
import com.gsm.platfra.api.entity.platfra.TPlatfra;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
@Table(name = "T_PLATFRA_BOARD")
@ToString
public class TPlatfraBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLATFRA_BOARD_SEQ", nullable = false)
    private Long platfraBoardSeq;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PLATFRA_ID", referencedColumnName = "PLATFRA_ID", nullable = false)
    private TPlatfra tPlatfra;
    
    @Size(max = 64)
    @NotNull
    @Column(name = "PLATFRA_ID", nullable = false, length = 64, insertable = false, updatable = false)
    private String platfraId;
    
    @Size(max = 100)
    @NotNull
    @Column(name = "SUBJECT", nullable = false, length = 100)
    private String subject;
    
    @Size(max = 512)
    @NotNull
    @Column(name = "DESCRIPTION", nullable = false, length = 512)
    private String description;
    
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
    
    @OneToMany(mappedBy = "tPlatfraBoard", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<TPlatfraBoardContent> tPlatfraBoardContentList = new ArrayList<>();

    public void update(PlatfraBoardDto boardDto){
        this.subject = boardDto.getSubject();
        this.description = boardDto.getDescription();
        this.modUserId = boardDto.getModUserId();
        this.modDate = Instant.now();
    }
}