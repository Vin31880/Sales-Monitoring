package com.project.receiverdomain.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class SalesInfoClient {
    private String brand;
    private String category;
    private String location;
    private LocalDate salesDate;
    private BigDecimal salesValue;
}
