package com.demo.employeemanagement.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

public class AdultValidator implements ConstraintValidator<Adult, LocalDate> {

    @Override
    public boolean isValid(LocalDate dobValue, ConstraintValidatorContext context) {
        return Period.between(dobValue, LocalDate.now()).getYears() >= 18;
    }
}
