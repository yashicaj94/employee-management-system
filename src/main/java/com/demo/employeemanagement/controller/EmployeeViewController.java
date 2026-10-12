package com.demo.employeemanagement.controller;

import com.demo.employeemanagement.dto.DepartmentDto;
import com.demo.employeemanagement.dto.EmployeeRequestDto;
import com.demo.employeemanagement.dto.EmployeeResponseDto;
import com.demo.employeemanagement.service.DepartmentService;
import com.demo.employeemanagement.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import java.io.IOException;
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
        model.addAttribute("managers", employeeService.getAll());
        return "employee-form";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        EmployeeResponseDto employee = employeeService.getById(id);
        model.addAttribute("employee", employee);
        
        model.addAttribute("managers", employeeService.getAll()
                                                                    .stream()
                                                                    .filter(e -> !e.getId().equals(id))
                                                                    .toList());
        model.addAttribute("profileImageBase64", employee.getProfileImageBase64());
        return "employee-form";
    }

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String save(@Valid @ModelAttribute("employee") EmployeeRequestDto dto,
                       BindingResult bindingResult, Model model,
                       @RequestParam(value = "profileImage", required = false) MultipartFile profileImage) throws IOException {

        if (bindingResult.hasErrors()) {
            if(dto.getId() == null) {
                model.addAttribute("managers", employeeService.getAll());
            } else {
                model.addAttribute("managers", employeeService.getAll()
                        .stream()
                        .filter(e -> !e.getId().equals(dto.getId()))
                        .toList());
                model.addAttribute("profileImageBase64", employeeService.getById(dto.getId()).getProfileImageBase64());
            }
            return "employee-form";
        }

        employeeService.save(dto, profileImage);

        return "redirect:/employee/getAll";
    }
}
