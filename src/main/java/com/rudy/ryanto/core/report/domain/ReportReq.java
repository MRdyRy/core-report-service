package com.rudy.ryanto.core.report.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportReq {
    private String transactionType;
    private String rekPengirim;
    private String rekPenerima;
    private String nominal;
    private LocalDateTime transactionDateTime;
    private String status;
}
