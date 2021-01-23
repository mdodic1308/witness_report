package com.helbiz.witness_report.geolocation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.MessageFormat;
import java.util.Objects;

@Service
public class GeolocationServiceIpify implements GeolocationService {
    Logger logger = LoggerFactory.getLogger(GeolocationServiceIpify.class);
    @Value("${ipify.api.url}")
    private String ipifyUrl;
    @Value("${ipify.api.key}")
    private String apiKey;

    private static final String UNKNOWN = "Unknown";
    private static final String API_KEY = "apiKey";
    private static final String IP_ADDRESS = "ipAddress";

    @Autowired
    RestTemplate restTemplate;

    @Override
    public String getCountryFromIp(String ipAddress) {
        IpInfo ipInfo =  getIpInfo(ipAddress);
        logger.info(MessageFormat.format("IP information from ipify API: {0}", ipInfo));
        String country = ipInfo.getLocation().getCountry();
        return country.equals("ZZ")? UNKNOWN : country;
    }

    private IpInfo getIpInfo(String ipAddress) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0");
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ipifyUrl)
                .queryParam(API_KEY, apiKey)
                .queryParam(IP_ADDRESS, ipAddress);

        ResponseEntity<IpInfo> responseEntity = restTemplate
                .exchange(builder.toUriString(), HttpMethod.GET, request,
                        IpInfo.class);
        return Objects.requireNonNull(responseEntity.getBody());
    }
}
