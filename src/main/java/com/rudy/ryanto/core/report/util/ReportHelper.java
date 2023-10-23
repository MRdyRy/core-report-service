package com.rudy.ryanto.core.report.util;

import com.rudy.ryanto.core.report.domain.Email;
import com.rudy.ryanto.core.report.exception.NotifEmailException;
import com.rudy.ryanto.core.report.exception.ReportPDFException;

public class ReportHelper {


    @SuppressWarnings("ConstantValue")
    public static <T> void doValidate(T c) {
        if (null == c) {
            if (c instanceof Email)
                throw new NotifEmailException(ReportConstant.error.DATA_NOT_VALID.getDescription());
            throw new ReportPDFException(ReportConstant.error.DATA_NOT_VALID.getDescription());
        }
    }
}
