package com.demo.employeemanagement.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = AdultValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Adult {
    String message() default "Age must be 18 years or more";
    Class <?> [] groups() default {};
    Class <? extends Payload> [] payload() default {};
}
