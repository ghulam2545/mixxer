package com.ghulam.account.utils;

import com.ghulam.account.enums.AccountVariant;
import com.ghulam.account.repos.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class AccountNumberGenerator {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private final AccountRepository accountRepository;

    public String generateAccountNumber(AccountVariant variant) {
        String prefix = switch (variant) {
            case ACCOUNT_SAVINGS -> "SAV";
            case ACCOUNT_SALARY -> "SAL";
            case ACCOUNT_PROPRIETOR -> "PRO";
            case ACCOUNT_CURRENT -> "CUR";
            case ACCOUNT_MINOR -> "MIN";
        };

        int randomNumber;
        do {
            randomNumber = SECURE_RANDOM.nextInt(100_000_000);
        } while (accountRepository.existsByAccountNumber(String.format("%s%08d", prefix, randomNumber)));
        return String.format("%s%08d", prefix, randomNumber);
    }
}
