package com.starheatingWO.starheatingWO.repository;


import com.starheatingWO.starheatingWO.model.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {

    List<Timesheet> findByExportedFalse();
}