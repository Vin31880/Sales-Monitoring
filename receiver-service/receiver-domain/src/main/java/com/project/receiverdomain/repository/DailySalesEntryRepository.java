package com.project.receiverdomain.repository;

import com.project.receiverdomain.entity.DailySalesEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DailySalesEntryRepository extends CrudRepository<DailySalesEntry, Long> {

    Optional<DailySalesEntry> findByRetailerIdAndMallCodeAndReceiptProviderCode(Long retailerId,
                                                                                String mallCode,
                                                                                String receiptProviderCode);

    Optional<DailySalesEntry> findByRetailer_RetailerProvidedIdAndMallCodeAndReceiptProviderCodeAndSalesDate(
            Long retailerProvidedId, String mallCode, String receiptProviderCode, LocalDate salesDate);

}
