package com.rudy.ryanto.core.report.helper;

import com.rudy.ryanto.core.report.domain.ReportReq;
import lombok.extern.slf4j.Slf4j;
import org.thymeleaf.context.Context;
import org.w3c.tidy.Tidy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import static com.rudy.ryanto.core.report.util.ReportConstant.UTF_8;

@Slf4j
public class PdfHelper {

    private Context context = null;

    public static String converToXHtml(String renderHtmlContent) throws UnsupportedEncodingException {
        log.info("convert to xhtml......!");
        Tidy tidy = new Tidy();
        tidy.setInputEncoding(UTF_8);
        tidy.setOutputEncoding(UTF_8);
        tidy.setXHTML(true);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(renderHtmlContent.getBytes(UTF_8));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        tidy.parseDOM(inputStream, outputStream);
        return outputStream.toString(UTF_8);
    }

    public static <T, S> Context context(S varName, T data) {
        Context context = new Context();
        context.setVariable(String.valueOf(varName), (ReportReq) data);
        return context;
    }
}
