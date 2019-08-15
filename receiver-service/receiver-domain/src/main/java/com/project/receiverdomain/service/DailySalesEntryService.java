package com.project.receiverdomain.service;

import com.project.receiverdomain.builder.DailySalesEntryBuilder;
import com.project.receiverdomain.entity.DailySalesEntry;
import com.project.receiverdomain.exceptions.NoSalesEntryFoundException;
import com.project.receiverdomain.exceptions.SalesEntryAlreadyExistsException;
import com.project.receiverdomain.model.SalesEntryRequest;
import com.project.receiverdomain.repository.DailySalesEntryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DailySalesEntryService {

    private static Logger logger = LoggerFactory.getLogger(DailySalesEntryService.class);

    private DailySalesEntryRepository dailySalesEntryRepository;
    private DailySalesEntryBuilder dailySalesEntryBuilder;
    private SalesAnalysisService salesAnalysisService;

    public DailySalesEntryService(@Autowired DailySalesEntryRepository dailySalesEntryRepository,
                                  @Autowired DailySalesEntryBuilder dailySalesEntryBuilder,
                                  @Autowired SalesAnalysisService salesAnalysisService) {
        this.dailySalesEntryRepository = dailySalesEntryRepository;
        this.dailySalesEntryBuilder = dailySalesEntryBuilder;
        this.salesAnalysisService = salesAnalysisService;
    }

    public DailySalesEntry pushSalesEntry(SalesEntryRequest salesEntryRequest) throws SalesEntryAlreadyExistsException {

        if (isSalesEntryRequestAlreadyExist(salesEntryRequest)) {
            throw new SalesEntryAlreadyExistsException("Sales Entry already exist with id "
                    + salesEntryRequest.getDailySalesSummaryId());
        }
        DailySalesEntry dailySalesEntry = dailySalesEntryRepository.save(buildDailySalesEntry(salesEntryRequest));

        if (null != dailySalesEntry) {
            SaveDataForAnalysis(dailySalesEntry);
        }
        return dailySalesEntry;
    }

    public List<DailySalesEntry> getAllSalesEntry() throws NoSalesEntryFoundException {
        List<DailySalesEntry> dailySalesEntryList = (List) dailySalesEntryRepository.findAll();
        if (dailySalesEntryList == null || dailySalesEntryList.size() == 0) {
            throw new NoSalesEntryFoundException("No sales entry found");
        }
        return (List<DailySalesEntry>) dailySalesEntryRepository.findAll();
    }

    private boolean isSalesEntryRequestAlreadyExist(SalesEntryRequest salesEntryRequest) {
        Optional<DailySalesEntry> optionalDailySalesEntry = dailySalesEntryRepository.
                findByRetailer_RetailerProvidedIdAndMallCodeAndReceiptProviderCodeAndSalesDate(
                        salesEntryRequest.getRetailerId(),
                        salesEntryRequest.getMallCode(),
                        salesEntryRequest.getReceiptProviderCode(),
                        salesEntryRequest.getShiftDate());
        if (optionalDailySalesEntry.isPresent()) {
            return true;
        }
        return false;
    }

    private DailySalesEntry buildDailySalesEntry(SalesEntryRequest salesEntryRequest) {
        return dailySalesEntryBuilder.build(salesEntryRequest);
    }

    private void SaveDataForAnalysis(DailySalesEntry dailySalesEntry) {
        try {
            salesAnalysisService.pushSalesAnalysisData(dailySalesEntry);
        } catch (Exception ex) {
            logger.error("Failed to save analysis data" + ex.getMessage());
        }
    }

}
