package com.gsm.platfra.api.dto.platfraboard;


import com.gsm.platfra.api.entity.platfraboard.TPlatfraBoard;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlatfraBoardContentDto {
  private Long contentSeq;
  private TPlatfraBoard tPlatfraBoard;
  private Long platfraBoardSeq;
  private Long contentNo;
  private String title;
  private String content;
  private Boolean delYn;
  private String regUserId;
  private Instant regDate;
  private String modUserId;
  private Instant modDate;
}
