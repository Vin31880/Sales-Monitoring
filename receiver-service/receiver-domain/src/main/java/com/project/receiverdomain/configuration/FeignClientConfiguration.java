package com.project.receiverdomain.configuration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {"com.project.receiverdomain.service.remote"})
public class FeignClientConfiguration {
}
