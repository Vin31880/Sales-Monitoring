package com.project.salesanalysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SalesAnalysisServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalesAnalysisServiceApplication.class, args);
    }

}
