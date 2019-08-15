package com.project.salesanalysis.controller;

import com.project.salesanalysis.model.SalesInfo;
import com.project.salesanalysis.service.SalesForecastingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = SalesAnalysisController.BASE_URL)
public class SalesAnalysisController {

    static final String BASE_URL = "/api/v1";
    private static final String SAVE_SALES_DATA_URL = "/sales-analysis";

    @Autowired
    private SalesForecastingService salesAnalysisService;

    @PostMapping(value = SAVE_SALES_DATA_URL)
    public ResponseEntity<?> saveSalesData(@RequestBody SalesInfo salesInfo) {
        if (null == salesInfo) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(salesAnalysisService.saveSalesData(salesInfo));
    }
}
