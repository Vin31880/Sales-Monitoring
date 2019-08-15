package com.project.receiverdomain.builder.remote;

import com.project.receiverdomain.entity.DailySalesEntry;
import com.project.receiverdomain.model.SalesInfoClient;
import org.springframework.stereotype.Component;

@Component
public class SalesInfoClientBuilder {

    public SalesInfoClient build(DailySalesEntry salesEntry) {
        SalesInfoClient salesInfoClient = new SalesInfoClient();
        salesInfoClient.setBrand(salesEntry.getBrand());
        salesInfoClient.setCategory(salesEntry.getCategory());
        salesInfoClient.setLocation(salesEntry.getRetailer() != null ?
                salesEntry.getRetailer().getRetailerLocation() : null);
        salesInfoClient.setSalesDate(salesEntry.getSalesDate());
        salesInfoClient.setSalesValue(salesEntry.getSalesValue());

        return salesInfoClient;
    }
}
