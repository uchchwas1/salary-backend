package org.example.salarysystem.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Requirement: Account type - savings/current [cite: 5]
    private String accountType;

    private String accountName;

    @Column(unique = true)
    private String accountNumber;

    // Requirement: Current balance [cite: 5]
    private Double currentBalance = 0.0;

    // Requirement: Bank and branch name [cite: 5]
    private String bankName;
    private String branchName;
}
