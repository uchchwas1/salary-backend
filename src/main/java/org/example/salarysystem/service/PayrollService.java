package org.example.salarysystem.service;


import org.example.salarysystem.entity.BankAccount;
import org.example.salarysystem.entity.CompanyAccount;
import org.example.salarysystem.entity.Employee;
import org.example.salarysystem.exception.InsufficientFundsException;
import org.example.salarysystem.repository.CompanyAccountRepository;
import org.example.salarysystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PayrollService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompanyAccountRepository companyAccountRepository;

    /**
     * Requirement: Calculate salary based on Grade and transfers money.
     * Logic:
     * - Grade 6 (Lowest) takes 'lowestGradeBasic' as input.
     * - Grade 5 = Grade 6 + 5000
     * - Grade 4 = Grade 5 + 5000 ... and so on.
     * - Total Salary = Basic + 20% Rent + 15% Medical.
     */
    @Transactional(rollbackFor = Exception.class)
    public String processMonthlySalary(Double lowestGradeBasic) {


        CompanyAccount companyAccount = companyAccountRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Company Account not found"));

        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()) {
            return "No employees found to pay.";
        }

        double totalRequiredAmount = 0;

        for (Employee emp : employees) {
            totalRequiredAmount += calculateTotalSalary(emp.getGrade(), lowestGradeBasic);
        }

        // Check for Insufficient Funds
        if (companyAccount.getBalance() < totalRequiredAmount) {
            throw new InsufficientFundsException("Insufficient funds. Required: " + totalRequiredAmount
                    + ", Available: " + companyAccount.getBalance());
        }

        // Process Transfer
        for (Employee emp : employees) {
            double salary = calculateTotalSalary(emp.getGrade(), lowestGradeBasic);

            // Debit Company
            companyAccount.setBalance(companyAccount.getBalance() - salary);

            // Credit Employee
            BankAccount empAccount = emp.getBankAccount();
            if (empAccount != null) {
                empAccount.setCurrentBalance(empAccount.getCurrentBalance() + salary);
            }
        }

        // 5. Save changes
        companyAccountRepository.save(companyAccount);
        employeeRepository.saveAll(employees); // Updates all employee bank balances

        return "Salary Processed Successfully. Total Paid: " + totalRequiredAmount;
    }

    private double calculateTotalSalary(int grade, double lowestGradeBasic) {

        double basic = lowestGradeBasic + ((6 - grade) * 5000);

        double houseRent = basic * 0.20;
        double medical = basic * 0.15;

        return basic + houseRent + medical;
    }

    public CompanyAccount addCompanyFunds(Double amount) {
        CompanyAccount account = companyAccountRepository.findById(1L)
                .orElse(new CompanyAccount());

        double currentBalance = (account.getBalance() == null) ? 0.0 : account.getBalance();
        account.setBalance(currentBalance + amount);

        return companyAccountRepository.save(account);
    }

    public CompanyAccount getCompanyBalance() {
        return companyAccountRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Company Account not created yet"));
    }
}
