package com.project.receiverdomain.service

import com.project.receiverdomain.builder.RetailerBuilder
import com.project.receiverdomain.entity.Retailer
import com.project.receiverdomain.exceptions.RetailerNotFoundException
import com.project.receiverdomain.repository.RetailerRepository
import spock.lang.Specification

class RetailerServiceSpec extends Specification {

    RetailerRepository retailerRepository
    RetailerBuilder retailerBuilder
    RetailerService retailerService

    void setup() {
        retailerRepository = Mock RetailerRepository
        retailerBuilder = Mock RetailerBuilder
        retailerService = new RetailerService(retailerRepository)
    }

    def "Success: find retailer by Id" () {
        given:
        Retailer retailer = new Retailer()
        retailer.setId(1L);

        when:
        Retailer result = retailerService.findRetailerById(1L)

        then:
        result.getId() == retailer.getId()
        1 * retailerRepository.findOptionalById(1L) >> Optional.of(retailer)
    }

    def "Exception: find retailer by Id"() {
        given:
        Retailer retailer = new Retailer()
        retailer.setId(1L);

        when:
        Retailer result = retailerService.findRetailerById(1L)

        then:
        1 * retailerRepository.findOptionalById(1L) >> Optional.empty()
        thrown(RetailerNotFoundException)
    }


}
