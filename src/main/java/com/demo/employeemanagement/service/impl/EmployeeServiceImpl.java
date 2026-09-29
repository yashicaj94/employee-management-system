package com.demo.employeemanagement.service.impl;

import com.demo.employeemanagement.dto.EmployeeRequestDto;
import com.demo.employeemanagement.dto.EmployeeResponseDto;
import com.demo.employeemanagement.entity.Employee;
import com.demo.employeemanagement.exception.ResourceNotFoundException;
import com.demo.employeemanagement.repository.DepartmentRepository;
import com.demo.employeemanagement.repository.EmployeeRepository;
import com.demo.employeemanagement.service.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final DepartmentRepository departmentRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<EmployeeResponseDto> getAll() {
        return employeeRepository.findAll(Sort.by("firstName", "lastName").ascending())
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public EmployeeResponseDto getById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee not found"));
        return toDto(employee);
    }

    @Override
    public EmployeeResponseDto save(EmployeeRequestDto dto) {
        Employee employee;
        if (dto.getId() != null) {
            employee = employeeRepository.findById(dto.getId()).orElseThrow(
                    () -> new ResourceNotFoundException("Employee not found"
                    ));
        } else {
            employee = new Employee();
        }

        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setDob(dto.getDob());
        employee.setDepartment(departmentRepository.findById(dto.getDepartmentId())
                                       .orElseThrow(() -> new ResourceNotFoundException("Department not found"
                                       )));
        employee.setSalary(dto.getSalary());
        if (dto.getManagerId() != null) {
            if (dto.getManagerId().equals(dto.getId())) {
                throw new IllegalArgumentException("Employee cannot be their own manager");
            } else {
                employee.setManager(employeeRepository.findById(dto.getManagerId())
                                            .orElseThrow(() -> new ResourceNotFoundException("Manager not found"
                                            )));
            }
        } else {
            employee.setManager(null);
        }

        employee = employeeRepository.save(employee);

        return toDto(employee);
    }

    private EmployeeResponseDto toDto(Employee employee) {
        EmployeeResponseDto dto = new EmployeeResponseDto();

        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setDob(employee.getDob());
        dto.setDepartmentId(employee.getDepartment().getId());
        dto.setDepartmentName(employee.getDepartment().getName());
        dto.setSalary(employee.getSalary());
        if (employee.getManager() != null) {
            dto.setManagerId(employee.getManager().getId());
            dto.setManagerName(employee.getManager().getFirstName() + " " + employee.getManager().getLastName());
        }
        return dto;
    }
}
