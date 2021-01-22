package com.helbiz.witness_report.model;

import com.helbiz.witness_report.validation.ExistedTitleValidation;
import com.helbiz.witness_report.validation.PhoneNumberValidation;
import lombok.Data;

@Data
public class ClientData {
    @ExistedTitleValidation
    private String title;
    @PhoneNumberValidation
    private String phone;
}
