package com.rudy.ryanto.core.report.services;

import com.rudy.ryanto.core.report.domain.ReportReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;

import static com.rudy.ryanto.core.report.helper.PdfHelper.context;
import static com.rudy.ryanto.core.report.helper.PdfHelper.converToXHtml;
import static com.rudy.ryanto.core.report.util.ReportHelper.doValidate;

@Slf4j
@Service
public class ReportService {

    @Autowired
    private ClassLoaderTemplateResolver classLoaderTemplateResolver;

    @Autowired
    private ITextRenderer iTextRenderer;

    public byte[] generatePdf(ReportReq sampleReq, HttpServletResponse httpServletResponse) {
        log.info("generate pdf run........!");
        byte[] pdf = null;

        doValidate(sampleReq);

        try {
            log.info("Initializing .......!");
            TemplateEngine templateEngine = new TemplateEngine();
            templateEngine.setTemplateResolver(classLoaderTemplateResolver);
            Context context = context("data", sampleReq);
            String renderHtmlContent = templateEngine.process("old", context);
            String xHtml = converToXHtml(renderHtmlContent);

            String fileName = sampleReq.getRekPengirim().concat(String.valueOf(sampleReq.getTransactionDateTime())) + "_" + System.currentTimeMillis();
            File output = new File(fileName + ".pdf");
            log.info("Initializing output path : {}", output.getPath());
            iTextRenderer.setDocumentFromString(xHtml);
            iTextRenderer.layout();

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            iTextRenderer.createPDF(outputStream);
            pdf = outputStream.toByteArray();
            outputStream.close();
            httpServletResponse.setContentType("application/pdf");
            httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + ".pdf\"");
            httpServletResponse.setHeader("Content-Filename", fileName + ".pdf");
            return pdf;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pdf;
    }

}
