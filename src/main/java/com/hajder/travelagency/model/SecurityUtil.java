package com.hajder.travelagency.model;


import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by Piotr on 30.12.2016.
 * @author Piotr Hajder
 */
public class SecurityUtil {
    private static final String DEFAULT_TECHNIQUE = "SHA-256";

    private static final Random random = new SecureRandom();

    private static byte[] generateSalt() {
        byte[] salt = new byte[32];
        random.nextBytes(salt);
        return salt;
    }

    private static byte[] stringToBytes(String input) {
        if (Base64.isBase64(input)) {
            return Base64.decodeBase64(input);
        }
        return Base64.encodeBase64(input.getBytes());
    }

    private static String bytesToString(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    private static byte[] getHashWithSalt(String input, byte[] salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance(DEFAULT_TECHNIQUE);
            digest.reset();
            digest.update(salt);
            return digest.digest(stringToBytes(input));
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Algorithm not found.");
        }
        return null;
    }

    public static String generateNewSalt() {
        return bytesToString(generateSalt());
    }

    public static String generateHashedString(String input, String salt) {
        byte[] byteSalt = stringToBytes(salt);
        return bytesToString(getHashWithSalt(input, byteSalt));
    }
}
