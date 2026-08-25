package com.ghulam.account.entities;

import com.ghulam.account.enums.AccountStatus;
import com.ghulam.account.enums.AccountVariant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer_account")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String accountNumber;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private String customerAddress;
    private AccountVariant variant;
    private AccountStatus status;
    private BigDecimal balance;
    private BigDecimal dailyLimit;

    @CreationTimestamp
    private LocalDateTime createdTimestamp;
    @UpdateTimestamp
    private LocalDateTime updatedTimestamp;

}
