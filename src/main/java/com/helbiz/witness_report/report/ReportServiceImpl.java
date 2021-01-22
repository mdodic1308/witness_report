package com.helbiz.witness_report.report;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder;
import com.helbiz.witness_report.geolocation.GeolocationService;
import com.helbiz.witness_report.model.ClientData;
import com.helbiz.witness_report.model.Report;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ReportServiceImpl implements ReportService {

    Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);
    @Autowired
    GeolocationService geolocationService;

    @Override
    public Report create(ClientData clientData, String ip) {
        Report report = new Report();
        report.setTitle(clientData.getTitle());
        report.setPhoneNumber(clientData.getPhone());
        report.setCountryByPhoneNumber(getCountryFromPhone(clientData.getPhone()));
        report.setCountryByIpAddress(getCountryFromIp(ip));
        return report;
    }

    private String getCountryFromPhone(String phone) {
        try {
            PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
            PhoneNumber phoneNumber = phoneNumberUtil.parse(phone, "US");
            PhoneNumberOfflineGeocoder phoneNumberOfflineGeocoder = PhoneNumberOfflineGeocoder.getInstance();
            logger.info("PHONE NUMBER GEO CODER DESCRIPTION: "+ phoneNumberOfflineGeocoder.getDescriptionForNumber(phoneNumber, Locale.forLanguageTag("US")));
            return phoneNumberUtil.getRegionCodeForNumber(phoneNumber);
        } catch (Exception e) {
            return null;
        }

    }

    private String getCountryFromIp(String ip) {
        return geolocationService.getCountryFromIp(ip);
    }

}
