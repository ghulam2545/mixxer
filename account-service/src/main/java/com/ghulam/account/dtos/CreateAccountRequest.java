package com.ghulam.account.dtos;

import com.ghulam.account.enums.AccountVariant;

import java.math.BigDecimal;

public record CreateAccountRequest(
        String customerName,
        String customerEmail,
        String customerPhone,
        String customerAddress,
        AccountVariant variant,
        BigDecimal initialDeposit
) {
}
