package net.kezzler.ssp.utils;

import java.util.UUID;

import org.apache.commons.lang.RandomStringUtils;

public class FrameworkRandomUtils {

    public static String generate_random_uuid() {
        return UUID.randomUUID().toString();
    }

    public static long generate_random_gtin() {
        String code = "978"+RandomStringUtils.random(9, false, true);
        code+=checkSum(code);
        return Long.valueOf(code);
    }

    public static Long generateRandomProductId() {
        return Long.valueOf(RandomStringUtils.random(10, false, true));
    }

    public static String generateRandomProductName() {
        return "Product" + RandomStringUtils.randomAlphabetic(20);
    }

    public static String generateRandomAliasName() {
        return "Alias" + RandomStringUtils.randomAlphabetic(20);
    }

    private static String checkSum(final String code) {
        int val = 0;
        for (int i = 0; i < code.length(); i++) {
            val += (Integer.parseInt(code.charAt(i) + "")) * ((i % 2 == 0) ? 1 : 3);
        }

        int checksum_digit = 10 - (val % 10);
        if (checksum_digit == 10)
            checksum_digit = 0;

        return String.valueOf(checksum_digit);
    }
}
