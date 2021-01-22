package com.helbiz.witness_report.validation;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberValidation, String> {
    Logger logger = LoggerFactory.getLogger(PhoneNumberValidator.class);

    @Override
    public void initialize(PhoneNumberValidation groovyScript) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {
            PhoneNumber phoneNumber= phoneUtil.parse(value, "US");
            logger.info("Phone number is: "+ phoneNumber);
            return phoneUtil.isValidNumber(phoneNumber);
        } catch (NumberParseException e) {
            return false;
        }
    }
}
