package com.gsm.platfra.system.security.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * JWT사용과 관련된 유틸리티 메소드를 제공한다.
 */
@Component
public class JwtUtil implements Serializable, InitializingBean {

	private static final long serialVersionUID = 5975614016740192337L;

	
	private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

	
	public static final String DEFAULT_AUTHORIZATION_HEADER = "Authorization";
	public static final String PROXY_AUTHORIZATION_HEADER   = "Proxy-Authorization";
	public static final String CLAIMS_AUTHORITIES_KEY       = "auth";
	public static final String DEFAULT_TOKEN_TYPE           = "Bearer";

	@Value("${security.jwt.secret-key:input-your-jwt-secret-key}")
	private String secret;
	
	@Value("${security.jwt.access-token-validity-in-milliseconds:43200000}") // 12시간
	private long accessTokenValidityInMilliseconds;

	@Value("${security.jwt.refresh-token-validity-in-milliseconds:43200000}")
	private long refreshTokenValidityInMilliseconds;

	private Key key;

	@Override
	public void afterPropertiesSet() throws Exception {
		byte[] keyBytes = Decoders.BASE64.decode(secret);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}

	public Key getJwtKey() {
		return key;
	}

	public static String getAuthorities(Authentication authentication) {
		return authentication.getAuthorities().stream()
							 .map(grantedAuthorities -> grantedAuthorities.getAuthority())
							 .collect(Collectors.joining(","));
	}

	public static String resolveToken(HttpServletRequest request) {
		return resolveToken(request, DEFAULT_AUTHORIZATION_HEADER);
	}

	public static String resolveToken(HttpServletRequest request, String authorizationHeader) {
		String token = request.getHeader(authorizationHeader);
		return resolveDefaultTypeToken(token);
	}

	private static String resolveDefaultTypeToken(String token) {
		if (StringUtils.hasText(token) && token.startsWith(DEFAULT_TOKEN_TYPE + " ")) {
			return token.substring(7);
		}

		return null;
	}
	
	public String createAccessToken(Authentication authentication) {
		String authorities = getAuthorities(authentication);
		Map<String, Object> claims = new HashMap<>();
		claims.put(CLAIMS_AUTHORITIES_KEY, authorities);

		return this.generateToken(authentication, claims, this.accessTokenValidityInMilliseconds);
	}

	
	public String createAccessToken(Authentication authentication, Map<String, Object> claims) {
		Map<String, Object> copiedClaims = new HashMap<>(claims);
		copiedClaims.put(CLAIMS_AUTHORITIES_KEY, getAuthorities(authentication));
		
		return this.generateToken(authentication, copiedClaims, this.accessTokenValidityInMilliseconds);
	}
	
//	보안을 위해 Framework jwtUtil에 제공하지 않음.
//	public String createAccessToken(Authentication authentication, Map<String, Object> claims, long accessTokenValidityInMilliseconds) {
//		Map<String, Object> copiedClaims = new HashMap<>(claims);
//		copiedClaims.put(CLAIMS_AUTHORITIES_KEY, getAuthorities(authentication));
//		return generateToken(authentication, copiedClaims, accessTokenValidityInMilliseconds);
//	}
	
	public String createRefreshToken(Authentication authentication) {
		String authorities = getAuthorities(authentication);
		Map<String, Object> claims = new HashMap<>();
		claims.put(CLAIMS_AUTHORITIES_KEY, authorities);

		return this.generateToken(authentication, claims, this.refreshTokenValidityInMilliseconds);
	}

	
	public String createRefreshToken(Authentication authentication, Map<String, Object> claims) {
		Map<String, Object> copiedClaims = new HashMap<>(claims);
		copiedClaims.put(CLAIMS_AUTHORITIES_KEY, getAuthorities(authentication));
		return this.generateToken(authentication, copiedClaims, this.refreshTokenValidityInMilliseconds);
	}

	
	// TODO plaintext -> from messageService
	public boolean validateToken(String token) {
		try {
			Jws<Claims> j = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
			log.error("잘못된 JWT 서명입니다.");
		} catch (ExpiredJwtException e) {
			log.error("만료된 JWT 토큰입니다.");
		} catch (UnsupportedJwtException e) {
			log.error("지원되지 않는 JWT 토큰입니다.");
		} catch (IllegalArgumentException e) {
			log.error("JWT 토큰이 잘못되었습니다.");
		}
		return false;
	}
	

	public Jws<Claims> parserToken(String token) {
		Jws<Claims> claimsJws = null;
		try {
			claimsJws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
		} catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
			log.error("잘못된 JWT 서명입니다.");
			throw new JwtException("잘못된 JWT 서명입니다.", e);
		} catch (ExpiredJwtException e) {
			log.error("만료된 JWT 토큰입니다.");
			throw new JwtException("만료된 JWT 토큰입니다.", e);
		} catch (UnsupportedJwtException e) {
			log.error("지원되지 않는 JWT 토큰입니다.");
			throw new UnsupportedJwtException("지원되지 않는 JWT 토큰입니다.", e);
		} catch (IllegalArgumentException e) {
			log.error("JWT 토큰이 잘못되었습니다.");
			throw new IllegalArgumentException("JWT 토큰이 잘못되었습니다.", e);
		}
		return claimsJws;
	}
	


	public Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}


	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	
	public Claims getAllClaimsFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody();
	}

	
	public String getUsernameFromToken(String token) {
		return this.getClaimFromToken(token, Claims::getSubject);
	}


	public Date getExpirationDateFromToken(String token) {
		return this.getClaimFromToken(token, Claims::getExpiration);
	}

	
	protected String generateToken(UserDetails userDetails, Map<String, Object> claims, long tokenValidityInMilliseconds) {
		return this.doGenerateToken(userDetails.getUsername(), claims, tokenValidityInMilliseconds);
	}

	
	protected String generateToken(Authentication authentication, Map<String, Object> claims, long tokenValidityInMilliseconds) {
		return this.doGenerateToken(authentication.getName(), claims, tokenValidityInMilliseconds);
	}

	
	private String doGenerateToken(String subject, Map<String, Object> claims, long tokenValidityInMilliseconds) {

		Date issuedAt = new Date();
		long now      = issuedAt.getTime();
		Date validity = new Date(now + tokenValidityInMilliseconds);

		if (log.isDebugEnabled()) {
			log.debug("subject : {}", subject);
			log.debug("claims  : {}", claims);
			log.debug("tokenValidityInMilliseconds : {}", tokenValidityInMilliseconds);
			log.debug("now : {}", LocalDateTime.ofInstant(Instant.ofEpochMilli(now), TimeZone.getDefault().toZoneId()));
			log.debug("validity : {}", LocalDateTime.ofInstant(Instant.ofEpochMilli(validity.getTime()),
					TimeZone.getDefault().toZoneId()));
		}

		return Jwts.builder()
				   .setClaims(claims)
				   .setSubject(subject) // must be here!
				   .signWith(key, SignatureAlgorithm.HS512)
				   .setIssuedAt(issuedAt)
				   .setExpiration(validity)
				   .compact();
	}

	
	
}
