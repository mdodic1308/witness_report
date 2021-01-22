package com.helbiz.witness_report.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = ExistedTitleValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistedTitleValidation {
    String message() default "Title is not in the list of the FBI wanted titles";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}