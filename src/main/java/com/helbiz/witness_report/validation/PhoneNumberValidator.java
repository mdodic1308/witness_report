package com.helbiz.witness_report.validation;

import com.helbiz.witness_report.phone.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberValidation, String> {
    @Autowired
    PhoneService phoneService;

    @Override
    public void initialize(PhoneNumberValidation groovyScript) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return phoneService.isValid(value);
    }
}
