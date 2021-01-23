package com.helbiz.witness_report.report;

import com.helbiz.witness_report.geolocation.GeolocationService;
import com.helbiz.witness_report.model.ClientData;
import com.helbiz.witness_report.model.Report;
import com.helbiz.witness_report.output.OutputService;
import com.helbiz.witness_report.phone.PhoneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class ReportServiceImpl implements ReportService {
    Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

    @Autowired
    GeolocationService geolocationService;
    @Autowired
    OutputService outputService;
    @Autowired
    PhoneService phoneService;

    @Override
    public Report create(ClientData clientData, String ip) {
        Report report = getReport(clientData, ip);
        outputService.write(report);
        return report;
    }

    private Report getReport(ClientData clientData, String ip) {
        Report report = new Report();
        report.setTitle(clientData.getTitle());
        report.setPhoneNumber(clientData.getPhone());
        report.setCountryByPhoneNumber(getCountryFromPhone(clientData.getPhone()));
        report.setCountryByIpAddress(getCountryFromIp(ip));
        logger.info(MessageFormat.format("Report with this data is created: {0}", report));
        return report;
    }

    private String getCountryFromPhone(String phone) {
        return phoneService.getCountryCode(phone);
    }

    private String getCountryFromIp(String ip) {
        return geolocationService.getCountryFromIp(ip);
    }
}
