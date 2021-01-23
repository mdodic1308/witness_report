package com.helbiz.witness_report.phone;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PhoneServiceLibPhoneNumberTest {
    PhoneService phoneService = new PhoneServiceLibPhoneNumber();

    @Test
    public void isValid_ShouldReturnTrue() {
        assertTrue(phoneService.isValid("+381645810568"));
    }

    @Test
    public void isValid_ShouldReturnFalse() {
        assertFalse(phoneService.isValid("12456489"));
    }

    @Test
    public void getCountryCode_ShouldReturnTrue() {
        assertEquals("RS",phoneService.getCountryCode("+381645810568"));
    }

    @Test
    public void getCountryCode_ShouldReturnNull() {
        assertNull(phoneService.getCountryCode("12456489"));
    }
}