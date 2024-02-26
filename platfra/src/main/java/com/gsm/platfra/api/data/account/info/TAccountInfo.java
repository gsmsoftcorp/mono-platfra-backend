package com.gsm.platfra.api.data.account.info;

import com.gsm.platfra.api.data.account.TAccount;
import com.gsm.platfra.api.data.base.BaseEntity;
import com.gsm.platfra.api.data.common.file.TCommonFile;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
@Table(name = "T_ACCOUNT_INFO")
public class TAccountInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_INFO_SEQ", nullable = false)
    private Long accountInfoSeq;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", nullable = false)
    private TAccount tAccount;

    @Column(name = "USER_ID", nullable = false, length = 64, insertable = false, updatable = false)
    private String userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FILE_SEQ", referencedColumnName = "FILE_SEQ", nullable = false)
    private TCommonFile tCommonFile;

    @Column(name = "FILE_SEQ", nullable = false, length = 64, insertable = false, updatable = false)
    private Long fileSeq;

    @Column(name = "MESSAGE")
    private String message;

    //구분값? 사업자 일반
    @Column(name = "TYPE")
    private String type;
}