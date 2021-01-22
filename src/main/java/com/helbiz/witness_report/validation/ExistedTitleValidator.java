package com.helbiz.witness_report.validation;

import com.helbiz.witness_report.wanted.WantedService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistedTitleValidator implements ConstraintValidator<ExistedTitleValidation, String> {

    @Autowired
    WantedService wantedService;

    @Override
    public void initialize(ExistedTitleValidation groovyScript) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return wantedService.getWantedTitles(null).contains(value);
    }
}
