package com.z80.services;

import java.math.BigInteger;
import java.security.SecureRandom;

public final class Security {

    public static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static SecureRandom random = new SecureRandom();

    public static String randString() {
        return new BigInteger(130, random).toString(32);
    }
}
