package com.helbiz.witness_report.web;

import com.helbiz.witness_report.model.ClientData;
import com.helbiz.witness_report.model.Report;
import com.helbiz.witness_report.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("${api.relative_path}")
public class ReportController {

    @Autowired
    ReportService reportService;

    @PostMapping
    public ResponseEntity<Report> addReport(@RequestBody @Valid ClientData report, HttpServletRequest request) {
        return new ResponseEntity<>(reportService.create(report, request.getRemoteAddr()), HttpStatus.CREATED);
    }
}
