package com.kreddit.security;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.kreddit.exception.SpringRedditException;
import com.kreddit.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
//@AllArgsConstructor
public class JwtProvider {

	private KeyStore keyStore;

	// @PostConstruct
	public String generateToken(Authentication authentication) {
		User principal = (User) authentication.getPrincipal();
		return Jwts.builder().setSubject(principal.getUsername()).signWith(getPrivateKey()).compact();
	}

	public void init() {
		try {
			keyStore = keyStore.getInstance("JKS");
			InputStream resourceAsStream = getClass().getResourceAsStream("/springblog.jks");
			keyStore.load(resourceAsStream, "secret".toCharArray());
		} catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
			throw new SpringRedditException("Exception occured while loading keystore");
		}
	}

	private PrivateKey getPrivateKey() {

		try {
			return (PrivateKey) keyStore.getKey("springblog", "secret".toCharArray());
		} catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
			throw new SpringRedditException("Exception occured while retriving public key from keystore");
		}
	}

	public boolean validateToken(String jwt) {
		Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(jwt);
		return true;
	}

	private PublicKey getPublicKey() {
		try {
			return keyStore.getCertificate("springblog").getPublicKey();
		} catch (KeyStoreException e) {
			throw new SpringRedditException("Exception occured while retrieving the public key from keystore");
		}
	}

	public String getUsernameFromJwt(String token) {

		Claims claims = Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(token).getBody();
		return claims.getSubject();

	}
}
