package com.helbiz.witness_report.wanted;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WantedServiceImpl implements WantedService {
    @Autowired
    private RestTemplate restTemplate;

    Logger logger = LoggerFactory.getLogger(WantedServiceImpl.class);

    private Wanted getWanted() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0");
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<Wanted> responseEntity = restTemplate
                .exchange("https://api.fbi.gov/wanted/v1/list", HttpMethod.GET, request,
                        new ParameterizedTypeReference<Wanted>() {
                        });
        return Objects.requireNonNull(responseEntity.getBody());
    }

    @Override
    public Set<String> getWantedTitles(Map<String, String> parameters) {
        Set<String> titles = getWanted().getItems().stream().map(Item::getTitle).collect(Collectors.toSet());
        logger.info("Current titles are (FBI wanted api): "+titles);
        return getWanted().getItems().stream().map(Item::getTitle).collect(Collectors.toSet());
    }
}
