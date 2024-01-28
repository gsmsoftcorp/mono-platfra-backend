package com.gsm.platfra.api.entity.account;

import com.gsm.platfra.api.entity.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
@Table(name = "T_ACCOUNT_INFO")
public class TAccountInfo extends BaseEntity {
    @Id
    @Column(name = "ACCOUNT_INFO_SEQ", nullable = false)
    private Long id;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID", nullable = false)
    private TAccount user;
    
}