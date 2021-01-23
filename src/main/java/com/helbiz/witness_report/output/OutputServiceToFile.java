package com.helbiz.witness_report.output;

import com.helbiz.witness_report.model.Report;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class OutputServiceToFile implements OutputService {
    @Value("${file.path}")
    private String filePath;
    @Override
    public synchronized boolean write(Report report) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(report.toString());
            bw.newLine();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
