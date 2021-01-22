package com.helbiz.witness_report.geolocation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IpInfo {

    private String ip;
    private Location location;
}
