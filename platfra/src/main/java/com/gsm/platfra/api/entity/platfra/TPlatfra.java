package com.gsm.platfra.api.entity.platfra;

import com.gsm.platfra.api.services.platfra.dto.table.PlatfraDto;
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
@Table(name = "T_PLATFRA")
public class TPlatfra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLATFRA_SEQ", nullable = false)
    private Long platfraSeq;
    
    @Size(max = 64)
    @NotNull
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
    
    @Column(name = "MAIN_CONTENT_SEQ", nullable = false)
    private Long mainContentSeq;
    
    @Size(max = 64)
    @Column(name = "OWNER_ID", nullable = false, length = 64)
    private String ownerId;
    
    @Column(name = "DEL_YN", nullable = false, length = 1)
    private Boolean delYn;
    
    @Size(max = 64)
    @Column(name = "REG_USER_ID", nullable = false, length = 64)
    private String regUserId;
    
    @Column(name = "REG_DATE", nullable = false)
    private Instant regDate;
    
    @Size(max = 64)
    @Column(name = "MOD_USER_ID", nullable = false, length = 64)
    private String modUserId;
    
    @Column(name = "MOD_DATE", nullable = false)
    private Instant modDate;
    
    @OneToMany(mappedBy = "tPlatfra", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<TPlatfraContent> tPlatfraContentList = new ArrayList<>();
    
    public void update(PlatfraDto platfraDto) {
        this.subject = platfraDto.getSubject();
        this.description = platfraDto.getDescription();
        this.introduction = platfraDto.getIntroduction();
    }
}