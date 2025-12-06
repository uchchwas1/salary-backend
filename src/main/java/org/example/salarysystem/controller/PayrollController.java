package org.example.salarysystem.controller;


import org.example.salarysystem.entity.CompanyAccount;
import org.example.salarysystem.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payroll")
@CrossOrigin("*")
public class PayrollController {

    @Autowired
    private PayrollService payrollService;

    // Requirement #17, #18: Calculate & Transfer Salary [cite: 17, 18]
    // Input: Lowest Grade Basic (as a query parameter for simplicity)
    @PostMapping("/pay-salary")
    public ResponseEntity<?> payMonthlySalary(@RequestParam Double lowestGradeBasic) {
        if (lowestGradeBasic == null || lowestGradeBasic <= 0) {
            return ResponseEntity.badRequest().body("Lowest grade basic salary must be positive.");
        }

        String result = payrollService.processMonthlySalary(lowestGradeBasic);
        return ResponseEntity.ok(Map.of("message", result));
    }

    // Requirement #20: Display remaining balance of company account [cite: 20]
    @GetMapping("/company/balance")
    public ResponseEntity<?> getCompanyBalance() {
        return ResponseEntity.ok(payrollService.getCompanyBalance());
    }

    // Requirement #10: Input option to add more money [cite: 10]
    @PostMapping("/company/add-money")
    public ResponseEntity<?> addCompanyMoney(@RequestBody Map<String, Double> payload) {
        Double amount = payload.get("amount");
        if (amount == null || amount <= 0) {
            return ResponseEntity.badRequest().body("Amount must be positive");
        }

        CompanyAccount updatedAccount = payrollService.addCompanyFunds(amount);
        return ResponseEntity.ok(updatedAccount);
    }
}