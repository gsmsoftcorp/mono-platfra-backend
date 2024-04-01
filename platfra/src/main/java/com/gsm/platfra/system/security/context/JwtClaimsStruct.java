package com.gsm.platfra.system.security.context;

import lombok.Data;

@Data
public class JwtClaimsStruct {
	private String userId; // 로그인유저ID
	private String SystemRole; //시스템 권한

	@Override
	public String toString() {
		return "JwtClaimsStruct [userId=" + userId + ",SystemRole=" + SystemRole + "]";
	}
}
