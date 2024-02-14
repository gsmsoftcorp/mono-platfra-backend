package com.gsm.platfra.api.data.common.error;

import com.gsm.platfra.api.data.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_COMMON_ERROR")
public class TCommonError extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ERROR_SEQ", nullable = false)
    private Long errorSeq;

    @Column(name = "URL", nullable = false)
    private String url;

    @Column(name = "LOCATION", nullable = false)
    private String location;

    @Column(name = "REQ_DATA", nullable = false)
    private String reqData;

    @Column(name = "ERROR_MSG", nullable = false)
    private String errorMsg;

    @Column(name = "RES_DATA")
    private String resData;



}