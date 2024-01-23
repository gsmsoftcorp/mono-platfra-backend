package com.gsm.platfra.api.services.board.dto.table;

import com.gsm.platfra.api.entity.platfra.TPlatfra;
import com.gsm.platfra.api.entity.platfraboard.TPlatfraBoardContent;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class PlatfraBoardDto {

    private Long platfraBoardSeq;
    private TPlatfra tPlatfra;
    private String platfraId;
    private String subject;
    private String description;
    private Boolean delYn;
    private String regUserId;
    private Instant regDate;
    private String modUserId;
    private Instant modDate;
    private List<TPlatfraBoardContent> tPlatfraBoardContentList = new ArrayList<>();
}
