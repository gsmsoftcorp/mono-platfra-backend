package com.gsm.platfra.api.data.common.file;

import com.gsm.platfra.api.data.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "T_COMMON_FILE")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TCommonFile extends BaseEntity {
    @Id
    @Column(name = "FILE_SEQ", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileSeq;
    
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
    
    public void setContentsInfo(String contentsCd, long contentsSeq) {
        this.contentsCd = contentsCd;
        this.contentsSeq = contentsSeq;
    }
}