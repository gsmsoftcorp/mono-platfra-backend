package com.gsm.platfra.api.entity.platfra;

import com.gsm.platfra.api.dto.platfra.PlatfraContentDto;
import com.gsm.platfra.api.entity.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
@Table(name = "T_PLATFRA_CONTENT")
public class TPlatfraContent extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment 때문에 선언됨
    @Column(name = "CONTENT_SEQ", nullable = false)
    private Long contentSeq;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PLATFRA_ID", referencedColumnName = "PLATFRA_ID", nullable = false) // note : 명시적으로라도 referencedColumnName 항상 기술해야 함
    private TPlatfra tPlatfra;
    
    @Size(max = 64)
    @NotNull
    @Column(name = "PLATFRA_ID", nullable = false, length = 64, insertable = false, updatable = false)
    private String platfraId;
    
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
    @Column(name = "VIEW", nullable = false)
    private Long view;

    public void update(PlatfraContentDto platfraContentDto) {
        this.title = platfraContentDto.getTitle();
        this.content = platfraContentDto.getContent();
    }
    
    public void updatePlatfraId(String platfraId) {
        this.platfraId = platfraId;
    }
}