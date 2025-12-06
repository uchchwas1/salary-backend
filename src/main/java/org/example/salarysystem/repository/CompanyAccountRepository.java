package org.example.salarysystem.repository;


import org.example.salarysystem.entity.CompanyAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyAccountRepository extends JpaRepository<CompanyAccount, Long> {
    // No custom methods needed.
    // We will rely on findById(1L) to get the main account.
}
