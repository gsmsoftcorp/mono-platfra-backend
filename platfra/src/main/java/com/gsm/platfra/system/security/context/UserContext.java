package com.gsm.platfra.system.security.context;

import lombok.Data;

import java.util.Date;

@Data
public class UserContext extends JwtClaimsStruct {
	
	private String accessToken; //Access Token
	private String sub; // sub (Subject) : 토큰 제목 - 토큰에서 사용자에 대한 식별값이 됨 (로그인 유저 ID: loginUserId)
	private Date iat; // iat (Issued At) : 토큰 발급일
	private Date exp; // exp (Expiration Time) : 토큰 만료일
	private String userIp; //사용자IP

//	private String jti; // jti (JWT Id) : JWT 토큰 식별자 (issuer가 여러명일 때 이를 구분하기 위한 값)
//	private String iss; // iss (Issuer) : 토큰 발급자
//	private String aud; // aud (Audience) : 토큰 대상자
//	private String nbf; // nbf (Not Before) : 토큰 활성 날짜 (이 날짜 이전의 토큰은 활성화 되지 않음을 보장)
	
	
	@Override
	public String toString() {
		return "UserContext [accessToken=" + accessToken + ", sub=" + sub + ", iat=" + iat + ", exp=" + exp
			+ ", userIp=" + userIp + ", toString()=" + super.toString() + "]";
	}
	
}
