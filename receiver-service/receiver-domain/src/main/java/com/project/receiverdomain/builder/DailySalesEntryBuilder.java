package com.project.receiverdomain.builder;

import com.project.receiverdomain.entity.DailySalesEntry;
import com.project.receiverdomain.model.SalesEntryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DailySalesEntryBuilder {

    @Autowired
    private RetailerBuilder retailerBuilder;

    public DailySalesEntry build(SalesEntryRequest salesEntryRequest) {

        DailySalesEntry dailySalesEntry = new DailySalesEntry();

        dailySalesEntry.setDailySalesEntryId(salesEntryRequest.getDailySalesSummaryId());
        dailySalesEntry.setLastModifiedDate(salesEntryRequest.getLastModifiedDate());
        dailySalesEntry.setSalesValue(salesEntryRequest.getShiftSalesValue());
        dailySalesEntry.setSalesDate(salesEntryRequest.getShiftDate());

        dailySalesEntry.setBrand(salesEntryRequest.getBrand());
        dailySalesEntry.setCategory(salesEntryRequest.getCategory());
        dailySalesEntry.setMallCode(salesEntryRequest.getMallCode());

        dailySalesEntry.setRetailer(retailerBuilder.buildRetailerFromSalesEntryRequest(salesEntryRequest));
        dailySalesEntry.setReceiptProviderCode(salesEntryRequest.getReceiptProviderCode());

        return dailySalesEntry;
    }

}
