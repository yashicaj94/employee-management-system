package com.demo.employeemanagement.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EmployeeResponseDto {

    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate dob;

    private Long departmentId;

    private String departmentName;

    private BigDecimal salary;

    private Long managerId;

    private String managerName;
}
