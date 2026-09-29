package com.demo.employeemanagement.controller;

import com.demo.employeemanagement.dto.DepartmentDto;
import com.demo.employeemanagement.dto.EmployeeRequestDto;
import com.demo.employeemanagement.service.DepartmentService;
import com.demo.employeemanagement.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeViewController {

    private final EmployeeService employeeService;

    private final DepartmentService departmentService;

    public EmployeeViewController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @GetMapping("/getAll")
    public String getAll(Model model) {
        model.addAttribute("employees", employeeService.getAll());
        return "employee-list";
    }

    @GetMapping("/getById/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("employee", employeeService.getById(id));
        return "employee-view";
    }

    @ModelAttribute("departments")
    public List<DepartmentDto> departments() {
        return departmentService.getAll();
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("employee", new EmployeeRequestDto());
        return "employee-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("employee") EmployeeRequestDto dto,
                       BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "employee-form";
        }

        employeeService.save(dto);

        return "redirect:/employee/getAll";
    }
}
