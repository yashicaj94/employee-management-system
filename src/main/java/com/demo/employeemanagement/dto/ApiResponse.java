package com.demo.employeemanagement.dto;

import lombok.Data;

@Data
public class ApiResponse<T> {

    private String message;

    private T data;
}
