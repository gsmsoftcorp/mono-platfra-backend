package com.gsm.platfra.api.dto.platfraboard;


import com.gsm.platfra.api.entity.platfraboard.TPlatfraBoard;
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
}
