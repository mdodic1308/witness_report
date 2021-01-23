package com.helbiz.witness_report.phone;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Locale;

@Service
public class PhoneServiceLibPhoneNumber implements PhoneService{
    Logger logger = LoggerFactory.getLogger(PhoneServiceLibPhoneNumber.class);


    @Override
    public boolean isValid(String phone) {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {
            Phonenumber.PhoneNumber phoneNumber= phoneUtil.parse(phone, "US");
            logger.info(MessageFormat.format("Phone number info: {0}", phoneNumber));
            return phoneUtil.isValidNumber(phoneNumber);
        } catch (NumberParseException e) {
            return false;
        }
    }

    @Override
    public String getCountryCode(String phone) {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        try {
            Phonenumber.PhoneNumber phoneNumber = phoneNumberUtil.parse(phone, "US");
            String countryCode= phoneNumberUtil.getRegionCodeForNumber(phoneNumber);
            logger.info(MessageFormat.format("Country code from phone number: {0}", countryCode));
            return countryCode;
        } catch (NumberParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getCountryName(String phone) {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        try {
            Phonenumber.PhoneNumber phoneNumber = phoneNumberUtil.parse(phone, "US");
            PhoneNumberOfflineGeocoder phoneNumberOfflineGeocoder = PhoneNumberOfflineGeocoder.getInstance();
            String country = phoneNumberOfflineGeocoder
                    .getDescriptionForNumber(phoneNumber, Locale.forLanguageTag("US"));
            logger.info(MessageFormat.format("Geo description from phone number: {0}", country));
            return country;
        } catch (NumberParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
