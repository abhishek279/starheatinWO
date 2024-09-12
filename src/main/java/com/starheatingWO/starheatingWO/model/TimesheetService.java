package com.starheatingWO.starheatingWO.model;



import com.starheatingWO.starheatingWO.repository.TimesheetRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimesheetService {

    @Autowired
    private TimesheetRepository timesheetRepository;

    public Timesheet saveTimesheet(Timesheet timesheet) {
        return timesheetRepository.save(timesheet);
    }

    public List<Timesheet> getAllTimesheets() {
        return timesheetRepository.findAll();
    }

}