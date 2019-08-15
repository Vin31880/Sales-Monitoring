package com.project.receiverdomain.service

import com.project.receiverdomain.builder.DailySalesEntryBuilder
import com.project.receiverdomain.entity.DailySalesEntry
import com.project.receiverdomain.exceptions.SalesEntryAlreadyExistsException
import com.project.receiverdomain.model.SalesEntryRequest
import com.project.receiverdomain.repository.DailySalesEntryRepository
import spock.lang.Specification

import java.time.LocalDate

class DailySalesEntryServiceSpec extends Specification {

    DailySalesEntryRepository dailySalesEntryRepository
    DailySalesEntryBuilder dailySalesEntryBuilder
    SalesAnalysisService salesAnalysisService
    DailySalesEntryService salesEntryService
    SalesEntryRequest salesEntryRequest

    void setup() {
        dailySalesEntryRepository = Mock(DailySalesEntryRepository)
        dailySalesEntryBuilder = Mock(DailySalesEntryBuilder)
        salesAnalysisService = Mock(SalesAnalysisService)
        salesEntryService = new DailySalesEntryService(dailySalesEntryRepository,
                dailySalesEntryBuilder, salesAnalysisService)
        salesEntryRequest = new SalesEntryRequest()
        salesEntryRequest.setDailySalesSummaryId(1L)
        salesEntryRequest.setBrand("testBrand")
        salesEntryRequest.setCategory("testCategory")
        salesEntryRequest.setMallCode("testMallCode")
        salesEntryRequest.retailerId = 2L
        salesEntryRequest.shiftDate = LocalDate.now()
        salesEntryRequest.receiptProviderCode = "testReceiptProvider"
    }

    def "Exception: push sales entry"() {
        when:
        salesEntryService.pushSalesEntry(salesEntryRequest)

        then:
        1 * dailySalesEntryRepository
                .findByRetailer_RetailerProvidedIdAndMallCodeAndReceiptProviderCodeAndSalesDate(2L,
                        "testMallCode", "testReceiptProvider", LocalDate.now()) >> Optional.of(salesEntryRequest)
        thrown(SalesEntryAlreadyExistsException)
    }

    def "Success: push sales entry"() {
        given:
        DailySalesEntry salesEntry = new DailySalesEntry()

        when:
        salesEntryService.pushSalesEntry(salesEntryRequest)

        then:
        1 * dailySalesEntryRepository
                .findByRetailer_RetailerProvidedIdAndMallCodeAndReceiptProviderCodeAndSalesDate(2L,
                        "testMallCode", "testReceiptProvider", LocalDate.now()) >> Optional.empty()
        1 * dailySalesEntryBuilder.build(salesEntryRequest) >> salesEntry
        1 * dailySalesEntryRepository.save(salesEntry) >> salesEntry
        1 * salesAnalysisService.pushSalesAnalysisData(salesEntry)
    }

}
