package com.project.receiverdomain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "DAILY_SALES_ENTRY")
public class DailySalesEntry {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dailySalesEntryId;

    private String brand;

    private String category;

    private LocalDate salesDate;

    private BigDecimal salesValue;

    private LocalDateTime lastModifiedDate;

    private String receiptProviderCode;

    private String mallCode;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "RetailerId")
    private Retailer retailer;

}
