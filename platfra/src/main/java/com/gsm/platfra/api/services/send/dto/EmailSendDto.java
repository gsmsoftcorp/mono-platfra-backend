package com.gsm.platfra.api.services.send.dto;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EmailSendDto {

  private String to;
  private String from;
  private String message;
  private String subject;
  private Map<String, Object> model;
  private String templateName;

}
