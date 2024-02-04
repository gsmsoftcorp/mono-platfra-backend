package com.gsm.platfra.api.data.feature.noti;

import com.gsm.platfra.api.data.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "T_FEATURE_NOTI")
public class TFeatureNotification extends BaseEntity {

    @Id
    @Column(name = "FEATURE_NOTI_SEQ", nullable = false)
    private Long featureNotiSeq;

    @Size(max = 16)
    @NotNull
    @Column(name = "CONTENTS_CD", nullable = false, length = 16)
    private String contentsCd;

    @NotNull
    @Column(name = "CONTENTS_SEQ", nullable = false)
    private Long contentsSeq;

    @NotNull
    @Column(name = "FEATURE_SEQ", nullable = false)
    private Long featureSeq;

    @Column(name = "NOTI_TARGET_USER_ID", nullable = false, length = 64)
    private String notiTargetUserId;

    @Column(name = "NOTI_TITLE", nullable = false, length = 100)
    private String notiTitle;

    @Column(name = "NOTI_DESCRIPTION", nullable = false, length = 500)
    private String notiDescription;

    @Column(name = "NOTI_URL", nullable = false, length = 500)
    private String notiUrl;

    @Column(name = "READ_YN", nullable = false, length = 1)
    private Boolean readYn;

    @Column(name = "SEND_YN", nullable = false, length = 1)
    private Boolean sendYn;

    public void updateReadYn() {
        this.readYn = true;
    }

    public void updateSendYn() {
        this.sendYn = true;
    }

}
