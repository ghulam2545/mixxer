package com.ghulam.account.controllers;

import com.ghulam.account.dtos.CreateAccountRequest;
import com.ghulam.account.dtos.CreateAccountResponse;
import com.ghulam.account.services.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping(path = "/createAccount")
    public ResponseEntity<CreateAccountResponse> createAccount(@Valid @RequestBody CreateAccountRequest request) {
        CreateAccountResponse out = accountService.createAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(out);
    }

    @PostMapping(path = "/getAccount")
    public ResponseEntity<CreateAccountResponse> getAccount(@RequestBody Map<String, String> request) {
        CreateAccountResponse out = accountService.getAccount(request);
        return ResponseEntity.status(HttpStatus.OK).body(out);
    }

    @PostMapping(path = "/getBalance")
    public ResponseEntity<?> getBalance(@RequestBody Map<String, String> request) {
        var out = accountService.getBalance(request);
        return ResponseEntity.status(HttpStatus.OK).body(out);
    }

    @PostMapping(path = "/deductBalance")
    public ResponseEntity<?> deductBalance(Map<String, String> request) {
        var out = accountService.deductBalance(request);
        return ResponseEntity.status(HttpStatus.OK).body(out);
    }

}
