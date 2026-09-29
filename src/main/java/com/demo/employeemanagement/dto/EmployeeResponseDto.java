package com.demo.employeemanagement.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmployeeResponseDto {

    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate dob;

    private String departmentName;

    private BigDecimal salary;

    private String managerName;
}
