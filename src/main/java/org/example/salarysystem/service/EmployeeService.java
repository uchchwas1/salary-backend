package org.example.salarysystem.service;

import org.example.salarysystem.dto.EmployeeDTO;
import org.example.salarysystem.entity.BankAccount;
import org.example.salarysystem.entity.Employee;
import org.example.salarysystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {

        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    @Transactional
    public Employee createEmployee(EmployeeDTO dto) {
        //uniq
        if (employeeRepository.existsByEmployeeId(dto.getEmployeeId())) {
            throw new RuntimeException("Employee ID " + dto.getEmployeeId() + " already exists.");
        }

        Employee emp = new Employee();
        emp.setEmployeeId(dto.getEmployeeId());
        emp.setName(dto.getName());
        emp.setGrade(dto.getGrade());
        emp.setAddress(dto.getAddress());
        emp.setMobile(dto.getMobile());

        // Create  Bank Account
        BankAccount account = new BankAccount();
        account.setAccountType(dto.getAccountType());
        account.setAccountName(dto.getAccountName());
        account.setAccountNumber(dto.getAccountNumber());
        account.setBankName(dto.getBankName());
        account.setBranchName(dto.getBranchName());
        account.setCurrentBalance(0.0);

        emp.setBankAccount(account);

        return employeeRepository.save(emp);
    }

    public Employee updateEmployee(Long id, EmployeeDTO dto) {
        Employee existingEmp = getEmployeeById(id);

        // Update Basic Info
        existingEmp.setName(dto.getName());
        existingEmp.setGrade(dto.getGrade());
        existingEmp.setAddress(dto.getAddress());
        existingEmp.setMobile(dto.getMobile());

        // Update Bank Details
        BankAccount account = existingEmp.getBankAccount();
        if (account != null) {
            account.setAccountType(dto.getAccountType());
            account.setAccountName(dto.getAccountName());
            account.setAccountNumber(dto.getAccountNumber());
            account.setBankName(dto.getBankName());
            account.setBranchName(dto.getBranchName());
        }

        return employeeRepository.save(existingEmp);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
