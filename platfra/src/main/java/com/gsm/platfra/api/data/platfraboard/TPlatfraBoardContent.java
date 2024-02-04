package com.gsm.platfra.api.data.platfraboard;

import com.gsm.platfra.api.data.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Builder
@Entity
@Table(name = "T_PLATFRA_BOARD_CONTENT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class TPlatfraBoardContent extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONTENT_SEQ", nullable = false)
    private Long contentSeq;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PLATFRA_BOARD_SEQ", referencedColumnName = "PLATFRA_BOARD_SEQ", nullable = false)
    private TPlatfraBoard tPlatfraBoard;

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
    @Column(name = "VIEW", nullable = false)
    private Long view;
    
    public void update(PlatfraBoardContentDto platfraBoardContentDto){
        this.content = platfraBoardContentDto.getContent();
        this.title = platfraBoardContentDto.getTitle();
    }
    
}