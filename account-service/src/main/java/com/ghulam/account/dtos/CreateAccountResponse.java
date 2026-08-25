package com.ghulam.account.dtos;

import com.ghulam.account.enums.AccountStatus;
import com.ghulam.account.enums.AccountVariant;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateAccountResponse(
        String id,
        String accountNumber,
        String customerName,
        String customerEmail,
        String customerPhone,
        String customerAddress,
        AccountVariant variant,
        AccountStatus status,
        BigDecimal balance,
        BigDecimal dailyLimit,
        LocalDateTime createdTimestamp
) {
}
