package com.project.receiverdomain.service;

import com.project.receiverdomain.entity.Retailer;
import com.project.receiverdomain.exceptions.RetailerNotFoundException;
import com.project.receiverdomain.repository.RetailerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RetailerService {

    private RetailerRepository retailerRepository;

    public RetailerService( @Autowired RetailerRepository retailerRepository) {
        this.retailerRepository = retailerRepository;
    }

    public Retailer findRetailerById(Long retailerId) throws RetailerNotFoundException {
        Optional<Retailer> retailerOptional = retailerRepository.findOptionalById(retailerId);
        if (!retailerOptional.isPresent()) {
            throw new RetailerNotFoundException("No retailer/tenant exists for id " + retailerId);
        }
        return retailerOptional.get();
    }
}
