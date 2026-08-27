package com.ghulam.account.services;

import com.ghulam.account.dtos.CreateAccountRequest;
import com.ghulam.account.dtos.CreateAccountResponse;
import com.ghulam.account.entities.CustomerAccount;
import com.ghulam.account.enums.AccountStatus;
import com.ghulam.account.repos.AccountRepository;
import com.ghulam.account.utils.AccountNumberGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

import static com.ghulam.account.utils.TransactionLimits.getTransactionLimit;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountNumberGenerator accountGenerator;

    public CreateAccountResponse createAccount(CreateAccountRequest request) {
        if (accountRepository.existsByCustomerPhone(request.customerPhone())) {
            throw new RuntimeException(String.format("Account with this phone [%s] number already exists.", request.customerPhone()));
        }

        CustomerAccount account = new CustomerAccount();
        account.setAccountNumber(accountGenerator.generateAccountNumber(request.variant()));
        account.setCustomerName(request.customerName());
        account.setCustomerEmail(request.customerEmail());
        account.setCustomerPhone(request.customerPhone());
        account.setCustomerAddress(request.customerAddress());
        account.setVariant(request.variant());
        account.setStatus(AccountStatus.ACCOUNT_ACTIVE);
        account.setBalance(request.initialDeposit());
        account.setDailyLimit(getTransactionLimit(request.variant()));

        try {
            CustomerAccount savedAccount = accountRepository.save(account);
            return new CreateAccountResponse(
                    savedAccount.getId(),
                    savedAccount.getAccountNumber(),
                    savedAccount.getCustomerName(),
                    savedAccount.getCustomerEmail(),
                    savedAccount.getCustomerPhone(),
                    savedAccount.getCustomerAddress(),
                    savedAccount.getVariant(),
                    savedAccount.getStatus(),
                    savedAccount.getBalance(),
                    savedAccount.getDailyLimit(),
                    savedAccount.getCreatedTimestamp()
            );
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public CreateAccountResponse getAccount(Map<String, String> request) {
        String accountNumber = request.getOrDefault("accountNumber", "");

        try {
            CustomerAccount account = accountRepository.findByAccountNumber(accountNumber)
                    .orElseThrow(() -> new RuntimeException(String.format("Account with this number [%s] does not exist.", accountNumber)));

            return new CreateAccountResponse(
                    account.getId(),
                    account.getAccountNumber(),
                    account.getCustomerName(),
                    account.getCustomerEmail(),
                    account.getCustomerPhone(),
                    account.getCustomerAddress(),
                    account.getVariant(),
                    account.getStatus(),
                    account.getBalance(),
                    account.getDailyLimit(),
                    account.getCreatedTimestamp()
            );
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Map<String, String> getBalance(Map<String, String> request) {
        String accountNumber = request.getOrDefault("accountNumber", "");

        try {
            CustomerAccount account = accountRepository.findByAccountNumber(accountNumber)
                    .orElseThrow(() -> new RuntimeException(String.format("Account with this number [%s] does not exist.", accountNumber)));

            return Map.of(
                    "accountNumber", account.getAccountNumber(),
                    "customerPhone", account.getCustomerPhone(),
                    "variant", account.getVariant().toString(),
                    "balance", account.getBalance().toString()
            );
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Map<String, String> deductBalance(Map<String, String> request) {
        String accountNumber = request.getOrDefault("accountNumber", "");
        String amount = request.getOrDefault("amount", "");

        try {
            CustomerAccount account = accountRepository.findByAccountNumber(accountNumber)
                    .orElseThrow(() -> new RuntimeException(String.format("Account with this number [%s] does not exist.", accountNumber)));

            if (account.getStatus() != AccountStatus.ACCOUNT_ACTIVE) {
                throw new RuntimeException(String.format("Account with this number [%s] is not active.", accountNumber));
            }

            if (account.getBalance().compareTo(account.getDailyLimit()) < 0) {
                throw new RuntimeException(String.format("Account with this number [%s] has insufficient balance.", accountNumber));
            }

            account.setBalance(account.getBalance().subtract(new BigDecimal(amount)));
            accountRepository.save(account);

            return Map.of(
                    "accountNumber", account.getAccountNumber(),
                    "customerPhone", account.getCustomerPhone(),
                    "variant", account.getVariant().toString(),
                    "balance", account.getBalance().toString()
            );
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Map<String, String> receiveBalance(Map<String, String> request) {
        String accountNumber = request.getOrDefault("accountNumber", "");
        String amount = request.getOrDefault("amount", "");

        try {
            CustomerAccount account = accountRepository.findByAccountNumber(accountNumber)
                    .orElseThrow(() -> new RuntimeException(String.format("Account with this number [%s] does not exist.", accountNumber)));

            if (account.getStatus() != AccountStatus.ACCOUNT_ACTIVE) {
                throw new RuntimeException(String.format("Account with this number [%s] is not active.", accountNumber));
            }

            account.setBalance(account.getBalance().add(new BigDecimal(amount)));
            accountRepository.save(account);

            return Map.of(
                    "accountNumber", account.getAccountNumber(),
                    "customerPhone", account.getCustomerPhone(),
                    "variant", account.getVariant().toString(),
                    "balance", account.getBalance().toString()
            );
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
