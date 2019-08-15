package com.project.receiverdomain.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Retailer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Unique identifier for retailer.")
    private Long id;
    private Long retailerProvidedId;
    private String retailerName;
    private String retailerLocation;
    private Integer retailerLicenseNumber;
}
