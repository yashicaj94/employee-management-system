package com.demo.employeemanagement.service.impl;

import com.demo.employeemanagement.dto.DepartmentDto;
import com.demo.employeemanagement.entity.Department;
import com.demo.employeemanagement.repository.DepartmentRepository;
import com.demo.employeemanagement.service.DepartmentService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<DepartmentDto> getAll() {
        return departmentRepository.findAll(Sort.by("name").ascending())
                .stream()
                .map(this::toDto)
                .toList();
    }

    private DepartmentDto toDto(Department department) {
        DepartmentDto dto = new DepartmentDto();

        dto.setId(department.getId());
        dto.setName(department.getName());

        return dto;
    }
}
