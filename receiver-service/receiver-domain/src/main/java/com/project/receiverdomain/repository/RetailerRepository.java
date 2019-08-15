package com.project.receiverdomain.repository;

import com.project.receiverdomain.entity.DailySalesEntry;
import com.project.receiverdomain.entity.Retailer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RetailerRepository extends JpaRepository<Retailer, Long> {
    Optional<Retailer> findOptionalById(Long retailerId);
}
