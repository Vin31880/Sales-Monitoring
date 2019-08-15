package com.project.salesanalysis.builder;

import com.project.salesanalysis.domain.SalesIndicator;
import com.project.salesanalysis.model.SalesInfo;
import org.springframework.stereotype.Component;

@Component
public class SalesIndicatorBuilder {

    public SalesIndicator build(SalesInfo salesInfo) {
        SalesIndicator salesIndicator = new SalesIndicator();
        salesIndicator.setBrand(salesInfo.getBrand());
        salesIndicator.setCategory(salesInfo.getCategory());
        salesIndicator.setSalesDate(salesInfo.getSalesDate());
        salesIndicator.setSalesValue(salesInfo.getSalesValue());
        salesIndicator.setLocation(salesInfo.getLocation());

        return salesIndicator;
    }
}
