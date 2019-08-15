package com.project.receiverdomain.builder;

import com.project.receiverdomain.entity.Retailer;
import com.project.receiverdomain.model.SalesEntryRequest;
import org.springframework.stereotype.Component;

@Component
public class RetailerBuilder {
    public Retailer buildRetailerFromSalesEntryRequest(SalesEntryRequest salesEntryRequest) {
        Retailer retailer = new Retailer();
        retailer.setRetailerProvidedId(salesEntryRequest.getRetailerId());
        retailer.setRetailerName(salesEntryRequest.getRetailerName());
        retailer.setRetailerLocation(salesEntryRequest.getRetailerLocation());
        retailer.setRetailerLicenseNumber(salesEntryRequest.getRetailerLicenseNumber());
        return retailer;
    }
}
