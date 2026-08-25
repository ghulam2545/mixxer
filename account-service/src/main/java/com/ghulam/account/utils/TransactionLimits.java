package com.ghulam.account.utils;

import com.ghulam.account.enums.AccountVariant;

import java.math.BigDecimal;
import java.util.Map;

public final class TransactionLimits {

    private TransactionLimits() {
    }

    private static final Map<AccountVariant, BigDecimal> transactionLimits = Map.of(
            AccountVariant.ACCOUNT_SAVINGS, BigDecimal.valueOf(100_000),
            AccountVariant.ACCOUNT_SALARY, BigDecimal.valueOf(300_000),
            AccountVariant.ACCOUNT_PROPRIETOR, BigDecimal.valueOf(300_000),
            AccountVariant.ACCOUNT_CURRENT, BigDecimal.valueOf(500_000),
            AccountVariant.ACCOUNT_MINOR, BigDecimal.valueOf(10_000)
    );

    public static BigDecimal getTransactionLimit(AccountVariant accountVariant) {
        return transactionLimits.get(accountVariant);
    }
}