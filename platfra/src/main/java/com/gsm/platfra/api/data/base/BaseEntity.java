package com.gsm.platfra.api.data.base;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class, AuditBaseEntityListener.class})
public abstract class BaseEntity implements AuditBaseEntity {
    @Column(name = "DEL_YN", nullable = false, length = 1)
    @Setter
    private Boolean delYn = false;
    
    @Column(name = "REG_USER_ID", nullable = false, length = 64)
    @Setter
    private String regUserId;
    
    @Column(name = "REG_DATE", nullable = false)
    @CreatedDate
    private LocalDateTime regDate;
    
    @Column(name = "MOD_USER_ID", nullable = false, length = 64)
    @Setter
    private String modUserId;
    
    @Column(name = "MOD_DATE", nullable = false)
    @LastModifiedDate
    private LocalDateTime modDate;
}