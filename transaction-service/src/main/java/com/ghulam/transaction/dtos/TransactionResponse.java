package com.ghulam.transaction.dtos;

import com.ghulam.transaction.enums.TransactionStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponse(
        String id,
        String senderAccountNumber,
        String receiverAccountNumber,
        BigDecimal amount,
        TransactionStatus status,
        String comment,
        String referenceNumber,
        LocalDateTime createdTimestamp,
        LocalDateTime completionTimestamp
) {
}
