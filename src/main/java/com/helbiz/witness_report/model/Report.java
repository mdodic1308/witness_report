package com.helbiz.witness_report.model;

import lombok.Data;

@Data
public class Report {
    private String title;
    private String phoneNumber;
    private String countryByPhoneNumber;
    private String countryByIpAddress;
}
