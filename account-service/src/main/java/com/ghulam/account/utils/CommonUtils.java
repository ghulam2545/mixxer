package com.ghulam.account.utils;

import com.ghulam.account.enums.AccountVariant;

import java.security.SecureRandom;

public class CommonUtils {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private CommonUtils() {
    }

    public static String generateAccountNumber(AccountVariant variant) {
        String prefix = switch (variant) {
            case ACCOUNT_SAVINGS -> "SAV";
            case ACCOUNT_SALARY -> "SAL";
            case ACCOUNT_PROPRIETOR -> "PRO";
            case ACCOUNT_CURRENT -> "CUR";
            case ACCOUNT_MINOR -> "MIN";
        };

        int randomNumber = SECURE_RANDOM.nextInt(100_000_000);
        return String.format("%s%08d", prefix, randomNumber);
    }

}
