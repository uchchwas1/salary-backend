package org.example.salarysystem.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CompanyAccount {
    @Id
    // We manually set ID=1 because there is only one company account [cite: 8]
    private Long id = 1L;

    // Requirement: The initial balance will be taken as input [cite: 8]
    // (This field stores the running balance)
    private Double balance;
}
