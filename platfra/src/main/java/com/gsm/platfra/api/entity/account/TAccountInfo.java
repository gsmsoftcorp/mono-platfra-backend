package com.gsm.platfra.api.entity.account;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "T_ACCOUNT_INFO")
public class TAccountInfo {
    @Id
    @Column(name = "ACCOUNT_INFO_SEQ", nullable = false)
    private Long id;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID", nullable = false)
    private TAccount user;
    
}