package com.gsm.platfra.api.entity.common;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "T_COMMON_FILE")
public class TCommonFile {
    @Id
    @Column(name = "FILE_SEQ", nullable = false)
    private Long id;
    
    @Size(max = 16)
    @NotNull
    @Column(name = "CONTENTS_CD", nullable = false, length = 16)
    private String contentsCd;
    
    @NotNull
    @Column(name = "CONTENTS_SEQ", nullable = false)
    private Long contentsSeq;
    
    @Size(max = 256)
    @NotNull
    @Column(name = "FILE_PATH", nullable = false, length = 256)
    private String filePath;
    
    @Size(max = 128)
    @NotNull
    @Column(name = "FILE_NAME", nullable = false, length = 128)
    private String fileName;
    
    @Size(max = 128)
    @NotNull
    @Column(name = "FILE_ENCODING_NAME", nullable = false, length = 128)
    private String fileEncodingName;
    
    @Size(max = 11)
    @NotNull
    @Column(name = "FILE_EXTENSION", nullable = false, length = 11)
    private String fileExtension;
    
    @Size(max = 16)
    @NotNull
    @Column(name = "FILE_SIZE", nullable = false, length = 16)
    private String fileSize;
    
    @NotNull
    @Column(name = "`ORDER`", nullable = false)
    private Integer order;
    
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