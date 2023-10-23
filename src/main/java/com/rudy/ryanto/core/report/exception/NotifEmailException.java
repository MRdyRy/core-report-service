package com.rudy.ryanto.core.report.exception;

public class NotifEmailException extends RuntimeException{
    public NotifEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotifEmailException(String message) {
        super(message);
    }
}
