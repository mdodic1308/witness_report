package com.helbiz.witness_report.output;

import com.helbiz.witness_report.model.Report;

public interface OutputService {

    boolean write(Report report);
}
