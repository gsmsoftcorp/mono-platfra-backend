package com.gsm.platfra.api.data.common.code;

import lombok.Getter;

@Getter
public enum CommonCodeEnum {

  CONTENTS("CONTENTS","ROOT"),
  PLATFRA("PLATFRA","CONTENTS"),
  PLATFRA_BOARD("PLATFRA_BOARD","PLATFRA"),
  PLATFRA_BOARD_COMMENT("PLATFRA_BOARD_COMMENT","PLATFRA_BOARD"),
  PLATFRA_BOARD_CONTENT("PLATFRA_BOARD_CONTENT","PLATFRA_BOARD"),
  PLATFRA_COMMENT("PLATFRA_COMMENT","PLATFRA"),
  PLATFRA_CONTENT("PLATFRA_CONTENT","PLATFRA"),
  ROLE("ROLE","ROOT"),
  ROLE_ADMIN("ROLE_ADMIN","ROLE"),
  ROLE_GUEST("ROLE_GUEST","ROLE"),
  ROLE_USER("ROLE_USER","ROLE"),
  ROOT("ROOT","");
  public final String commonCd;
  public final String parentCd;

  CommonCodeEnum(String commonCd, String parentCd){
    this.commonCd = commonCd;
    this.parentCd = parentCd;
  }
}
