package com.ghulam.transaction.entities;

import com.ghulam.transaction.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "account_transaction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String senderAccountNumber;
    private String receiverAccountNumber;
    private BigDecimal amount;
    private TransactionStatus status;
    private String comment;
    private String referenceNumber;

    @CreationTimestamp
    private LocalDateTime createdTimestamp;
    private LocalDateTime completionTimestamp;
}
