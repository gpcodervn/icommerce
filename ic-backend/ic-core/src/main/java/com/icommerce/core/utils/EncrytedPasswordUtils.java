package com.icommerce.core.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncrytedPasswordUtils {
	
	private EncrytedPasswordUtils() {
		super();
	}
	
	/*
	 * Encryte Password with BCryptPasswordEncoder
	 */
	public static String encrytePassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}

}
