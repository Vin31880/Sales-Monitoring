package com.project.receiverdomain.service.remote;

import com.project.receiverdomain.model.SalesInfoClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@FeignClient(url = "http://localhost:8081", name = "SALES-ANALYSIS-SERVICE")
public interface SalesAnalysisClient {

    final String BASE_URL = "/api/v1";
    final String SAVE_SALES_DATA_URL = BASE_URL + "/sales-analysis";

    @PostMapping(value = SAVE_SALES_DATA_URL)
    public ResponseEntity<?> saveSalesData(@RequestBody SalesInfoClient salesInfo);
}
