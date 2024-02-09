package com.gsm.platfra.api.data.platfra.saved;

import com.gsm.platfra.api.data.base.BaseEntity;
import com.gsm.platfra.api.data.platfra.TPlatfra;
import jakarta.persistence.*;
import lombok.*;

@Getter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_PLATFRA_CONTENT_SAVE")
public class ContentSave extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONTENT_SAVE_SEQ")
    private Long contentSaveSeq;

    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @JoinColumn(name = "PLATFRA_ID", referencedColumnName = "PLATFRA_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private TPlatfra platfra;

    @Column(name = "PLATFRA_ID", nullable = false, length = 64, insertable = false, updatable = false)
    private String platfraId;

    @Column(name = "CONTENT_SEQ", nullable = false, updatable = false)
    private Long contentSeq;

    @Column(name = "PLATFRA_BOARD_SEQ", nullable = false, updatable = false)
    private Long platfraBoardSeq;

    @Builder
    public ContentSave(String userId, TPlatfra platfra, Long contentSeq, Long platfraBoardSeq) {
        this.userId = userId;
        this.platfra = platfra;
        this.platfraId = platfra.getPlatfraId();
        this.contentSeq = contentSeq;
        this.platfraBoardSeq = platfraBoardSeq;
    }
}
