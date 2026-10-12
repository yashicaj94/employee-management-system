package com.demo.employeemanagement.repository;

import com.demo.employeemanagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    boolean existsByFirstNameAndLastNameAndDob(String firstName, String lastName, LocalDate dob);

    boolean existsByFirstNameAndLastNameAndDobAndIdNot(String firstName, String lastName, LocalDate dob, Long id);
}
