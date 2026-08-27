package com.ghulam.transaction.dtos;

public record TransactionRequest(
        String senderAccountNumber,
        String receiverAccountNumber,
        String amount
) {
}
