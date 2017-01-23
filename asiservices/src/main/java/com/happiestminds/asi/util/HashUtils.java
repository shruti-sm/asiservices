package com.happiestminds.asi.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.happiestminds.asi.exception.AsiException;

/**
 * Secure hash wrapper.
 * 
 * @author Anoop.Elias
 *
 */
public class HashUtils {

	private static final String HASH_ALGORITHM = "SHA-256";
	private static final int SALT_LENGTH = 8;
	private static final int ITERATIONS = 1024;

	private static char[] HEX_ARRAY = "0123456789abcdef".toCharArray();

	/**
	 * Creates a hash with salt.
	 * 
	 * @return
	 */
	public static String hash(String key) {
		byte[] salt = SecureRandomString.getBytes(SALT_LENGTH);
		return encodeHex(combine(salt, hash(key, salt)));

	}

	/**
	 * Generate a Hash without adding salt.
	 * 
	 * @param key
	 * @return
	 */
	public static String hashWithoutSalt(String key) {
		return encodeHex(hash(key, new byte[0]));
	}

	/**
	 * Generate hash from key and salt.
	 * 
	 * @param key
	 * @param salt
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private static byte[] hash(String key, byte[] salt) {
		try {
			byte[] value = combine(salt, key.getBytes(Charset.forName("UTF-8")));

			MessageDigest digester = MessageDigest.getInstance(HASH_ALGORITHM);
			for (int i = 0; i < ITERATIONS; i++) {
				value = digester.digest(value);
			}
			return value;

		} catch (NoSuchAlgorithmException e) {
			throw new AsiException("Exception during hashing", e);
		}
	}


	/**
	 * Matches key with hash assuming salt is there in hash.
	 * 
	 * @param key
	 * @param hash
	 * @return
	 */
	public static boolean matches(String key, String hash) {
		byte[] digest = decodeHex(hash);
		if(digest == null)
			return false;
		
		byte[] salt = subArray(digest, 0, SALT_LENGTH);
		if(salt == null)
			return false;

		byte[] inputHash = subArray(digest, SALT_LENGTH, digest.length);
		if(inputHash == null)
			return false;

		byte[] actualHash = hash(key, salt);

		return matches(inputHash, actualHash);
	}

	/**
	 * Get sub array from original.
	 * 
	 * @param digest
	 * @param from
	 * @param length
	 * @return
	 */
	private static byte[] subArray(byte[] digest, int from, int length) {
		
		if(digest.length >= length) {
			byte[] salt = new byte[length - from];
			for (int i = from; i < length; i++) {
				salt[i - from] = digest[i];
			}
			return salt;
		}
		
		return null;
	}

	/**
	 * Combine byte arrays.
	 * 
	 * @param byt
	 * @return
	 */
	public static byte[] combine(byte[]... byt) {
		int size = 0;
		for (byte[] by : byt)
			size += by.length;

		byte[] ret = new byte[size];
		int index = 0;
		for (int i = 0; i < byt.length; i++) {
			for (int j = 0; j < byt[i].length; j++) {
				ret[index++] = byt[i][j];
			}
		}

		return ret;
	}

	/**
	 * http://stackoverflow.com/questions/9655181/convert-from-byte-array-to-hex
	 * -string-in-java
	 * 
	 * @param bytes
	 * @return
	 */
	public static String encodeHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = HEX_ARRAY[v >>> 4];
			hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
		}
		return new String(hexChars);
	}

	/**
	 * http://stackoverflow.com/questions/140131/convert-a-string-representation
	 * -of-a-hex-dump-to-a-byte-array-using-java
	 * 
	 * @param s
	 * @return
	 */
	public static byte[] decodeHex(String s) {
		int len = s.length();
		
		if(len % 2 != 0)
			return null;
		
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
					.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

	/**
	 * http://codahale.com/a-lesson-in-timing-attacks/
	 * 
	 * Constant time comparison to prevent against timing attacks.
	 */
	private static boolean matches(byte[] expected, byte[] actual) {
		if (expected.length != actual.length) {
			return false;
		}

		int result = 0;
		for (int i = 0; i < expected.length; i++) {
			result |= expected[i] ^ actual[i];
		}
		return result == 0;
	}
}
