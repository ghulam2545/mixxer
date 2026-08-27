package com.ghulam.transaction.controller;

import com.ghulam.transaction.dtos.TransactionRequest;
import com.ghulam.transaction.dtos.TransactionResponse;
import com.ghulam.transaction.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    public ResponseEntity<TransactionResponse> transfer(TransactionRequest request) {
        TransactionResponse out = transactionService.transfer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(out);
    }

    public ResponseEntity<TransactionResponse> getTransaction(Map<String, String> request) {
        TransactionResponse out = transactionService.getTransaction(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(out);
    }
}
