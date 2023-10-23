package com.rudy.ryanto.core.report.controller;

import com.rudy.ryanto.core.report.domain.ReportReq;
import com.rudy.ryanto.core.report.services.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/report")
@Slf4j
public class ReportController {

    @Autowired
    private ReportService reportService;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/pdf", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] generatePdf(@RequestBody ReportReq sampleReq, HttpServletResponse httpServletResponse) {
        log.info("generate pdf call : with request {}", sampleReq);
        return reportService.generatePdf(sampleReq, httpServletResponse);
    }

}
