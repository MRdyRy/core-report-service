package com.rudy.ryanto.core.report.exception;

public class ReportPDFException extends RuntimeException{

    public ReportPDFException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReportPDFException(String message) {
        super(message);
    }
}
