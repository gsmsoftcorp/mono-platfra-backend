package com.gsm.platfra.api.data.platfraboard;


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
