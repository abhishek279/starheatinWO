package com.starheatingWO.starheatingWO.repository;

import com.starheatingWO.starheatingWO.model.ServiceReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// This interface extends JpaRepository, which provides CRUD operations and more
@Repository
public interface ServiceReportRepository extends JpaRepository<ServiceReport, Long> {
    List<ServiceReport> findByExportedFalse();
}
