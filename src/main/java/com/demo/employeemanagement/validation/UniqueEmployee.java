package com.demo.employeemanagement.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = UniqueEmployeeValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UniqueEmployee {
    String message() default "Employee with the same first name, last name and date of birth already exists";
    Class <?> [] groups() default {};
    Class <? extends Payload> [] payload() default {};
}
