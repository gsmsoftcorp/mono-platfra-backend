package com.gsm.platfra.api.data.platfra.saved;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentSaveDto {
    private Long contentSaveSeq;
    private String userId;
    private String platfraId;
    private Long contentSeq;
    private Long platfraBoardSeq;
}
