package com.happiestminds.asi.util;

import java.security.SecureRandom;

public class SecureRandomString {
	
	private static final int DEFAULT_KEY_LENGTH = 16;
	
	/**
	 * http://stackoverflow.com/questions/1461568/is-securerandom-thread-safe
	 */
	private static final SecureRandom random = new SecureRandom();
	
	/**
	 * Get default length random String.
	 * 
	 * @return
	 */
	public static String get() {
		return HashUtils.encodeHex(getBytes());
	}

	/**
	 * Returns a default number of random bytes.
	 * 
	 * @return
	 */
	public static byte[] getBytes() {
		return getBytes(DEFAULT_KEY_LENGTH);
	}

	/**
	 * Get the specified number of random bytes.
	 * 
	 * @param keyLength
	 * @return
	 */
	public static byte[] getBytes(int keyLength) {
		byte[] bytes = new byte[keyLength];
        random.nextBytes(bytes);
        
        return bytes;
	}
	
}
