package com.gsm.platfra.system.security.context;

import com.gsm.platfra.system.security.context.AbstractContext;
import lombok.Data;

import java.util.List;

@Data
public class JwtClaimsStruct extends AbstractContext {
	private String userId; // 로그인유저ID
	private String SystemRole; //시스템 권한

	@Override
	public String toString() {
		return "JwtClaimsStruct [userId=" + userId + ",SystemRole=" + SystemRole + "]";
	}
}
