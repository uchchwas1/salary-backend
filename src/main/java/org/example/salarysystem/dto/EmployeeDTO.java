package org.example.salarysystem.dto;


import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class EmployeeDTO {


    @NotBlank(message = "Employee ID is required")
    @Pattern(regexp = "\\d{4}", message = "Employee ID must be exactly 4 digits")
    private String employeeId;

    @NotBlank(message = "Name is required")
    private String name;

    @Min(value = 1, message = "Grade must be between 1 and 6")
    @Max(value = 6, message = "Grade must be between 1 and 6")
    private int grade;

    private String address;
    private String mobile;


    @NotBlank(message = "Account Type is required")
    private String accountType;

    @NotBlank(message = "Account Name is required")
    private String accountName;

    @NotBlank(message = "Account Number is required")
    private String accountNumber;

    @NotBlank(message = "Bank Name is required")
    private String bankName;

    @NotBlank(message = "Branch Name is required")
    private String branchName;
}