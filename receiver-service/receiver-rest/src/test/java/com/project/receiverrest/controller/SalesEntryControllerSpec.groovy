package com.project.receiverrest.controller

import com.project.receiverdomain.entity.DailySalesEntry
import com.project.receiverdomain.exceptions.SalesEntryAlreadyExistsException
import com.project.receiverdomain.model.SalesEntryRequest
import com.project.receiverdomain.service.DailySalesEntryService
import com.project.receiverdomain.validator.custom.SalesEntryRequestValidator
import com.project.receiverdomain.validator.custom.ValidateSalesEntryRequest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import javax.validation.ConstraintValidatorContext
import java.time.LocalDate

import static org.springframework.http.HttpStatus.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

class SalesEntryControllerSpec extends Specification {

    DailySalesEntryService dailySalesEntryService
    SalesEntryController salesEntryController
    SalesEntryRequest salesEntryRequest
    MockMvc mvc
    String salesEntryRequestToString
    SalesEntryRequestValidator salesEntryRequestValidator
    ConstraintValidatorContext constraintValidatorContext


    void setup() {
        dailySalesEntryService = Mock(DailySalesEntryService)
        salesEntryController = new SalesEntryController(dailySalesEntryService)
        buildSalesEntryRequest()
        salesEntryRequestToString = "{\n" +
                "\t\"brand\" : \"testBrand\",\n" +
                "  \"retailerName\" : \"testRetailerName\",\n" +
                "  \"retailerLocation\" : \"testRetailerLocation\",\n" +
                "  \"category\" : \"testCategory\",\n" +
                "  \"receiptProviderCode\" : \"testReceiptProviderCode\",\n" +
                "  \"shiftDate\" : \"2019-05-06\",\n" +
                "  \"shiftSalesValue\" : \"2134.34\",\n" +
                "  \"mallCode\" : \"testMallCode\",\n" +
                "  \"dailySalesSummaryId\" : \"32\", \n" +
                "  \"retailerId\" : \"2\" \n" +
                "}"
        salesEntryRequestValidator = new SalesEntryRequestValidator();
        constraintValidatorContext = Mock(ConstraintValidatorContext)
        mvc = standaloneSetup(salesEntryController).build()
    }

    private void buildSalesEntryRequest() {
        salesEntryRequest = new SalesEntryRequest()
        salesEntryRequest.setDailySalesSummaryId(1L)
        salesEntryRequest.brand = "testBrand"
        salesEntryRequest.category = "testCategory"
        salesEntryRequest.mallCode = "testMallCode"
        salesEntryRequest.retailerId = 2L
        salesEntryRequest.shiftDate = LocalDate.now()
        salesEntryRequest.receiptProviderCode = "testReceiptProvider"
    }

    def "Success: test push sales entry"() {
        given:
        def request = post("/api/v1/sales")
        request.contentType(MediaType.APPLICATION_JSON_UTF8)
        request.content(salesEntryRequestToString)
        ValidateSalesEntryRequest validateSalesEntryRequest = Mock()
        dailySalesEntryService.pushSalesEntry(salesEntryRequest) >> new DailySalesEntry()

        when:
        def response = mvc.perform(request).andReturn().response

        then:
        salesEntryRequestValidator.initialize(validateSalesEntryRequest)
        salesEntryRequestValidator.isValid(salesEntryRequest, constraintValidatorContext) >> true
        response.status == CREATED.value()
    }

    def "Exception: Bad request push sales entry"() {
        given:
        def request = post("/api/v1/sales")
        request.contentType(MediaType.APPLICATION_JSON_UTF8)
        request.content("invalid data")

        when:
        def response = mvc.perform(request).andReturn().response

        then:
        response.status == BAD_REQUEST.value()
    }

    def "Exception: conflict push sales entry"() {
        given:
        def request = post("/api/v1/sales")
        request.contentType(MediaType.APPLICATION_JSON_UTF8)
        request.content(salesEntryRequestToString)
        dailySalesEntryService.pushSalesEntry(_) >> { throw new SalesEntryAlreadyExistsException("Exception") }

        when:
        def response = mvc.perform(request).andReturn().response

        then:
        response.status == CONFLICT.value()
    }

}
