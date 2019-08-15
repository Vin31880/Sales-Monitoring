package com.project.salesanalysis.service;

import com.project.salesanalysis.builder.SalesIndicatorBuilder;
import com.project.salesanalysis.domain.SalesIndicator;
import com.project.salesanalysis.model.SalesInfo;
import com.project.salesanalysis.repository.SalesIndicatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesForecastingService {

    @Autowired
    SalesIndicatorRepository mallSalesRepository;

    @Autowired
    SalesIndicatorBuilder salesIndicatorBuilder;

    public SalesIndicator saveSalesData(SalesInfo salesInfo) {
        return mallSalesRepository.saveAndFlush(salesIndicatorBuilder.build(salesInfo));
    }
}
