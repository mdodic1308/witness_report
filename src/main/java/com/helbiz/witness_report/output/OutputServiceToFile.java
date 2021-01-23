package com.helbiz.witness_report.output;

import com.helbiz.witness_report.model.Report;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class OutputServiceToFile implements OutputService {

    @Override
    public synchronized boolean write(Report report) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("reports.txt", true))) {
            bw.write(report.toString());
            bw.newLine();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
