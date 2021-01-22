package com.helbiz.witness_report.wanted;

import java.util.Map;
import java.util.Set;

public interface WantedService {

    public Set<String> getWantedTitles(Map<String, String> parameters);
}
