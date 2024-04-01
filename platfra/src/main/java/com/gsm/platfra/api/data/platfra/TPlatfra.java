package com.gsm.platfra.api.data.platfra;

import com.gsm.platfra.api.data.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
@Table(name = "T_PLATFRA")
public class TPlatfra extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLATFRA_SEQ", nullable = false)
    private Long platfraSeq;
    
    @NotNull
    @Column(name = "PLATFRA_ID", nullable = false, length = 64)
    private String platfraId;
    
    @NotNull
    @Column(name = "SUBJECT", nullable = false, length = 64)
    private String subject;
    
    @NotNull
    @Column(name = "DESCRIPTION", nullable = false, length = 512)
    private String description;
    
    @NotNull
    @Column(name = "INTRODUCTION", nullable = false, length = 1024)
    private String introduction;
    
    @Column(name = "MAIN_CONTENT_SEQ", nullable = false)
    private Long mainContentSeq;
    
    @Column(name = "OWNER_ID", nullable = false, length = 64)
    private String ownerId;
    
    @OneToMany(mappedBy = "tPlatfra", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<TPlatfraContent> tPlatfraContentList = new ArrayList<>();
    
    public void update(PlatfraDto platfraDto) {
        this.subject = platfraDto.getSubject();
        this.description = platfraDto.getDescription();
        this.introduction = platfraDto.getIntroduction();
    }
}