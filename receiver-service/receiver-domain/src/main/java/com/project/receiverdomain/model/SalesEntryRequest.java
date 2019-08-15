package com.project.receiverdomain.model;

import com.project.receiverdomain.validator.custom.ValidateSalesEntryRequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ValidateSalesEntryRequest
public class SalesEntryRequest {

    private String retailerName;

    private String brand;

    private String retailerLocation;

    private String category;

    private LocalDate shiftDate;

    private BigDecimal shiftSalesValue;

    private LocalDateTime lastModifiedDate;

    private Long retailerId;

    private Long dailySalesSummaryId;

    private String receiptProviderCode;

    private String mallCode;

    private Integer retailerLicenseNumber;
}
