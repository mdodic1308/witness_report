package com.helbiz.witness_report.report;

import com.helbiz.witness_report.model.ClientData;
import com.helbiz.witness_report.model.Report;

public interface ReportService {

    Report create(ClientData report, String ip);
}
