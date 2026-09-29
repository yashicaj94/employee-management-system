package com.demo.employeemanagement.service;

import com.demo.employeemanagement.dto.EmployeeRequestDto;
import com.demo.employeemanagement.dto.EmployeeResponseDto;

import java.util.List;

public interface EmployeeService {

    List<EmployeeResponseDto> getAll();

    EmployeeResponseDto getById(Long id);

    EmployeeResponseDto save(EmployeeRequestDto dto);
}
