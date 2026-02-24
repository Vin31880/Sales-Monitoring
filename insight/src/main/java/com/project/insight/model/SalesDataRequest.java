package com.project.model;

import lombok.Data;
import java.util.List;

@Data
public class SalesDataRequest {
    private String siteId;
    private String siteName;
    private List<SaleRecord> records;

    @Data
    public static class SaleRecord {
        private String date;
        private double revenue;
        private int transactions;
    }
}
