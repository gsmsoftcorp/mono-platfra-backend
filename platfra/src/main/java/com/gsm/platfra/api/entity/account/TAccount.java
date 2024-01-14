package com.gsm.platfra.api.entity.account;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "T_ACCOUNT")
public class TAccount {
    @Id
    @Size(max = 64)
    @Column(name = "USER_ID", nullable = false, length = 64)
    private String userId;
    
    @Size(max = 64)
    @Column(name = "EMAIL", length = 64)
    private String email;
    
    @Size(max = 11)
    @Column(name = "PHONE", length = 11)
    private String phone;
    
    @Size(max = 64)
    @NotNull
    @Column(name = "PASSWORD", nullable = false, length = 64)
    private String password;
    
    @Size(max = 64)
    @Column(name = "USER_NM", length = 64)
    private String userNm;
    
    @Column(name = "AGE")
    private Integer age;
    
    @Column(name = "GENDER")
    private Character gender;
    
    @Column(name = "BIRTHDAY")
    private LocalDate birthday;
    
    @Column(name = "BANNED_YN")
    private Integer bannedYn;
    
    @NotNull
    @Column(name = "DEL_YN", nullable = false)
    private Character delYn;
    
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
    
}