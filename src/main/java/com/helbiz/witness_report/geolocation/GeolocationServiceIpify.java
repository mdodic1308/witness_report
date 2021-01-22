package com.helbiz.witness_report.geolocation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@Service
public class GeolocationServiceIpify implements GeolocationService {

    Logger logger = LoggerFactory.getLogger(GeolocationServiceIpify.class);
    @Autowired
    RestTemplate restTemplate;

    @Override
    public String getCountryFromIp(String ipAddress) {
        IpInfo ipInfo =  getIpInfo(ipAddress);
        logger.info("##### IP info: " + ipInfo);
        return ipInfo.getLocation().getCountry();
    }

    private IpInfo getIpInfo(String ipAddress) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0");
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(headers);
        String url = "https://geo.ipify.org/api/v1";
        String apiKey = "at_U7tHKWEOMtmbLLQblXTBG9KZIJILX";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("apiKey", apiKey)
                .queryParam("ipAddress", ipAddress);

        ResponseEntity<IpInfo> responseEntity = restTemplate
                .exchange(builder.toUriString(), HttpMethod.GET, request,
                        IpInfo.class);
        logger.info("Ipifi api is called");
        return Objects.requireNonNull(responseEntity.getBody());
    }
}
