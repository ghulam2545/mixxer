package com.ghulam.account.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;

import java.math.BigDecimal;
import java.util.Map;

import static com.ghulam.account.utils.KafkaEvents.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountEventConsumer {

    private final AccountService accountService;

    @KafkaListener(topics = TRANSACTION_COMPLETED, groupId = ACCOUNT_SERVICE_GROUP)
    public void transactionCompleted(@Payload JsonNode payload) {
        log.info("============== Received transaction completed event: {}", payload.toString());

        try {
            String receiverAccountNumber = payload.path("receiverAccountNumber").asString();
            BigDecimal amount = payload.path("amount").asDecimal();

            accountService.receiveBalance(Map.of(
                    "accountNumber", receiverAccountNumber,
                    "amount", amount.toString()
            ));
        } catch (Exception e) {
            throw new RuntimeException("Error processing transaction completed event: " + e.getMessage(), e);
        }
    }

    @KafkaListener(topics = FRAUD_DETECTED, groupId = ACCOUNT_SERVICE_GROUP)
    public void fraudDetected(@Payload JsonNode payload) {
        log.info("============== Received fraud detected event: {}", payload.toString());

        try {
            String accountNumber = payload.path("accountNumber").asString();

            accountService.blockAccount(Map.of(
                    "accountNumber", accountNumber
            ));
        } catch (Exception e) {
            throw new RuntimeException("Error processing fraud detected event: " + e.getMessage(), e);
        }
    }
}