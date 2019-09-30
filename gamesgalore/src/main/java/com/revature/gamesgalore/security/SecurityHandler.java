package com.revature.gamesgalore.security;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import com.revature.gamesgalore.GamesgaloreApplication;
import com.revature.gamesgalore.exceptions.ResponseExceptionManager;
import com.revature.gamesgalore.springimpl.AccountDetails;
import com.revature.gamesgalore.springimpl.AccountDetailsService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class SecurityHandler {
	private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	private String secretKey;

	public SecurityHandler() {
		try (InputStream input = new FileInputStream(GamesgaloreApplication.PROPETIES_FILE)) {
			Properties prop = new Properties();
			prop.load(input);
			secretKey = prop.getProperty("security.jwt.token.secret-key");
		} catch (IOException ex) {
			LogManager.getLogger().error(ex);
		}
	}

	@Autowired
	private AccountDetailsService accountDetailsService;

	public String createJWT(String username, List<String> roles) {

		Claims claims = Jwts.claims();
		claims.put("roles", roles);
		claims.put("username", username);

		Instant instant = Instant.now();
		Date now = Date.from(instant);
		final Long amountToAdd = 1L;
		final ChronoUnit unit = ChronoUnit.DAYS;
		Date expireAt = Date.from(instant.plus(amountToAdd, unit));

		return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(expireAt)
				.signWith(signatureAlgorithm, secretKey).compact();
	}

	public Jws<Claims> getJwsClaims(String jwt) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt);
	}

	public String getUsername(String jwt) {
		return (String) (Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody().get("username"));
	}

	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

	public Authentication getAuthentication(String jwt) {
		AccountDetails accountDetails = accountDetailsService.loadUserByUsername(getUsername(jwt));
		return new UsernamePasswordAuthenticationToken(accountDetails, "", accountDetails.getAuthorities());
	}

	public boolean isNotExpired(String jwt) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt);
			return claims.getBody().getExpiration().after(new Date());
		} catch (JwtException | IllegalArgumentException e) {
			throw ResponseExceptionManager.getRSE(HttpStatus.UNAUTHORIZED, ResponseExceptionManager.UNAUTHORIZED).get();
		}
	}

	public boolean isAuthorizedJWT(String jwt, String expectedAuthority) {
		Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt);
		String role = (String) claims.getBody().get("roleName");
		return expectedAuthority.equalsIgnoreCase(role);
	}

	public String encodeData(String data) {
		return Base64.getEncoder().encodeToString(data.getBytes());
	}

	public String hash(CharSequence rawPassword) {
		return BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt());
	}

	public boolean hashMatches(CharSequence rawPassword, String hashedPassword) {
		return BCrypt.checkpw(rawPassword.toString(), hashedPassword);
	}
}
