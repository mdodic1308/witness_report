package com.helbiz.witness_report.phone;

public interface PhoneService {

    boolean isValid(String phone);

    String getCountryCode(String phone);

    String getGeoDescription(String phone);
}
