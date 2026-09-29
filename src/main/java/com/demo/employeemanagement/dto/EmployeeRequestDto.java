package com.demo.employeemanagement.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EmployeeRequestDto {

    private Long id;

    @NotBlank(message = "First name is required")
    @Size(max = 255, message = "First name must not exceed 255 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 255, message = "Last name must not exceed 255 characters")
    private String lastName;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dob;

    @NotNull(message = "Department is required")
    private Long departmentId;

    @NotNull(message = "Salary is required")
    @Positive(message = "Salary should be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Salary must not exceed 9999999999.99 and can have up to 2 decimal " +
            "places")
    private BigDecimal salary;

    private Long managerId;
}