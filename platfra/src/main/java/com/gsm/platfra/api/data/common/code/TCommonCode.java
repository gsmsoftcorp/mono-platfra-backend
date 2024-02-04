package com.gsm.platfra.api.data.common.code;

import com.gsm.platfra.api.data.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_COMMON_CODE")
public class TCommonCode extends BaseEntity {
    @Id
    @Column(name = "COMMON_CD", nullable = false, length = 16)
    private String commonCd;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PARENT_CD", referencedColumnName = "COMMON_CD", nullable = false) // note : 명시적으로라도 referencedColumnName 항상 기술해야 함
    private TCommonCode parentCode;
    
    @Column(name = "PARENT_CD", length = 16, insertable = false, updatable = false)
    private String parentCd;
    
    @Column(name = "NAME", length = 16)
    private String name;
    
    @Column(name = "DESCRIPTION", length = 256)
    private String description;
    
    @Column(name = "TYPE", nullable = false, length = 8)
    private String type;
    
    @Column(name = "ORDER", nullable = false, length = 16)
    private String order;
    
    @OneToMany(mappedBy = "parentCode")
    @Builder.Default
    private List<TCommonCode> childCodeList = new ArrayList<>();
    
    
}