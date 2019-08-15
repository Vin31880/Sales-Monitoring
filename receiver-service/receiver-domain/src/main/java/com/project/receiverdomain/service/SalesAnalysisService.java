package com.project.receiverdomain.service;

import com.project.receiverdomain.builder.remote.SalesInfoClientBuilder;
import com.project.receiverdomain.entity.DailySalesEntry;
import com.project.receiverdomain.service.remote.SalesAnalysisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesAnalysisService {

    private SalesAnalysisClient salesAnalysisClient;

    public SalesAnalysisService(@Autowired SalesAnalysisClient salesAnalysisClient) {
        this.salesAnalysisClient = salesAnalysisClient;
    }

    public void pushSalesAnalysisData(DailySalesEntry salesEntry) {
        salesAnalysisClient.saveSalesData(new SalesInfoClientBuilder().build(salesEntry));
    }

}
