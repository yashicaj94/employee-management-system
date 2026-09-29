package com.demo.employeemanagement.controller;

import com.demo.employeemanagement.dto.ApiResponse;
import com.demo.employeemanagement.dto.EmployeeRequestDto;
import com.demo.employeemanagement.dto.EmployeeResponseDto;
import com.demo.employeemanagement.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<EmployeeResponseDto>> getAll() {
        return ResponseEntity.ok(employeeService.getAll());
    }

    @GetMapping("/getById")
    public ResponseEntity<EmployeeResponseDto> getById(@RequestParam Long id) {
        return ResponseEntity.ok(employeeService.getById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<EmployeeResponseDto>> save(@Valid @RequestBody EmployeeRequestDto dto) {
        ApiResponse<EmployeeResponseDto> apiResponse = new ApiResponse<>();

        apiResponse.setMessage(dto.getId() != null ? "Employee updated successfully" : "Employee added successfully");
        apiResponse.setData(employeeService.save(dto));

        return ResponseEntity.ok(apiResponse);
    }
}
