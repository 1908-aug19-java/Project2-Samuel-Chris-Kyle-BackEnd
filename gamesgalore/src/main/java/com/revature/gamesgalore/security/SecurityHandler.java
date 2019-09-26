package com.revature.gamesgalore.security;

import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class SecurityHandler {
	private static final Logger LOGGER = LogManager.getLogger();
	@Autowired
	private static SignatureAlgorithm signatureAlgorithm;
	private static Key signingKey;

	static {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
			keyGenerator.init(256, new SecureRandom());
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] apiKeySecretBytes = secretKey.getEncoded();
			signatureAlgorithm = SignatureAlgorithm.HS256;
			signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error(e);
		}
	}

	public Jws<Claims> getJwsClaims(String jwt) {
		return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(jwt);
	}

	public boolean isAuthenticatedJWT(String jwt, HttpServletRequest request) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(jwt);
			String ip = (String) claims.getBody().get("IP");
			String email = (String) claims.getBody().get("email");
			return email != null && request.getRemoteAddr().equals(ip);
		} catch (JwtException e) {
			LOGGER.info(e);
		}
		return false;
	}

	public boolean isAuthorizedJWT(String jwt, String expectedAuthority) {
		Jws<Claims> claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(jwt);
		String authority = (String) claims.getBody().get("authority");
		return expectedAuthority.equalsIgnoreCase(authority);
	}

	public String encodeData(String data) {
		return Base64.getEncoder().encodeToString(data.getBytes());
	}

	public String createJWT(String subject, Map<String, String> claims, Long amountToAdd, ChronoUnit unit) {

		Instant instant = Instant.now();
		Date now = Date.from(instant);
		Date expireAt = Date.from(instant.plus(amountToAdd, unit));	
		JwtBuilder builder = Jwts.builder().setIssuedAt(now).setSubject(subject);
		for (Map.Entry<String, String> entry: claims.entrySet()) {
			builder.claim(entry.getKey(), claims.get(entry.getKey()));
		}
		builder.claim("IAT", now.getTime());
		builder.claim("EXP", expireAt.getTime());
		builder.setExpiration(expireAt).signWith(signatureAlgorithm, signingKey);
		LOGGER.info(expireAt);
		return builder.compact();
	}

	public void encryptionSchema() {
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			PublicKey publicKey = keyPair.getPublic();
			PrivateKey privateKey = keyPair.getPrivate();

			Cipher rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			rsa.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] ciphertext = rsa.doFinal("my cleartext".getBytes());

			rsa.init(Cipher.DECRYPT_MODE, privateKey);
			String cleartext = new String(rsa.doFinal(ciphertext));
			LOGGER.info(cleartext);
		} catch (GeneralSecurityException e) {
			LOGGER.error(e);
		}
	}

	public String hash(CharSequence rawPassword) {
		return BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt());
	}

	public boolean hashMatches(CharSequence rawPassword, String hashedPassword) {
		return BCrypt.checkpw(rawPassword.toString(), hashedPassword);
	}
}
