package com.demo.employeemanagement.validation;

import com.demo.employeemanagement.dto.EmployeeRequestDto;
import com.demo.employeemanagement.repository.DepartmentRepository;
import com.demo.employeemanagement.repository.EmployeeRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class UniqueEmployeeValidator implements ConstraintValidator<UniqueEmployee, EmployeeRequestDto> {

    private final EmployeeRepository employeeRepository;

    public UniqueEmployeeValidator(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public boolean isValid(EmployeeRequestDto dto, ConstraintValidatorContext context) {
        if(dto == null || dto.getFirstName() == null || dto.getLastName() == null || dto.getDob() == null)
            return true;

        boolean duplicate;

        if (dto.getId() != null) {
            duplicate = employeeRepository.existsByFirstNameAndLastNameAndDobAndIdNot(dto.getFirstName(),
                                                                                      dto.getLastName(),
                                                                                      dto.getDob(), dto.getId());
        } else {
            duplicate = employeeRepository.existsByFirstNameAndLastNameAndDob(dto.getFirstName(), dto.getLastName(),
                                                                              dto.getDob());
        }

        return !duplicate;
    }
}
