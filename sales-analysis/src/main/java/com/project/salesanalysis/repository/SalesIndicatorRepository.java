package com.project.salesanalysis.repository;

import com.project.salesanalysis.domain.SalesIndicator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesIndicatorRepository extends JpaRepository<SalesIndicator, Long> {
}
