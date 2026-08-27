package com.ghulam.account.repos;

import com.ghulam.account.entities.CustomerAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<CustomerAccount, String> {

    boolean existsByCustomerPhone(String customerPhone);
    boolean existsByAccountNumber(String accountNumber);
    Optional<CustomerAccount> findByAccountNumber(String accountNumber);
}
