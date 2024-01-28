package com.gsm.platfra.api.entity.account;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;

@Builder
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
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
    private Boolean bannedYn;
    
    @NotNull
    @Column(name = "DEL_YN", nullable = false, length = 1)
    private Boolean delYn;
    
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

    protected TAccount() {}

    @Builder
    private TAccount(String userId, String email, String phone, String password, String userNm, Integer age, Character gender, LocalDate birthday) {
        this.userId = userId;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.userNm = userNm;
        this.age = age;
        this.gender = gender;
        this.birthday = birthday;
    }
    
}