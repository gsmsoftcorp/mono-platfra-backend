package com.gsm.platfra.api.entity.account;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "T_ACCOUNT")
public class TAccount {
//    @Id
//    @Size(max = 64)
//    @Column(name = "USER_ID", nullable = false, length = 64)
//    private String userId;
// id가 String인 이유를 모르겠어서 일단 바꿔뒀습니다.
    @Id // 식별자 등록
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
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

    @Column(name= "NICK_NAME",nullable = false, updatable = true, unique = true)
    private String nickName;

    @Column(name="PROFILE_IMAGE", length = -1)
    private String profileImage;

    @Column(name="ACCOUNT_STATUS", length = 20, nullable = false)
    private TAccountStatus tAccountStatus = TAccountStatus.ACTIVE;

    @Column(name = "PROVIDER", nullable = false)
    private Provider provider = Provider.LOCAL;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    public TAccount(String userId, String email, String nickName, String profileImage) {
        this.userId = userId;
        this.email = email;
        this.nickName = nickName;
        this.profileImage = profileImage;
    }

    public enum Provider {
        LOCAL("기본 회원"),
        GOOGLE("구글 연동 회원"),
        KAKAO("카카오 연동 회원"),
        NAVER("네이버 연동 회원");

        @Getter
        private String provider;

        Provider(String provider) {
            this.provider = provider;
        }
    }

    public enum TAccountStatus {
        ACTIVE("활동중"),
        SLEEP("휴면 상태"),
        QUIT("탈퇴 상태");

        @Getter
        private String status;

        TAccountStatus(String status) {
            this.status = status;
        }
    }


}