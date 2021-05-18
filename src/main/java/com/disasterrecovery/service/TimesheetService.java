package com.disasterrecovery.service;

import com.disasterrecovery.model.Timesheet;
import com.disasterrecovery.repository.TimesheetRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimesheetService {

  @Autowired
  TimesheetRepo timesheetRepo;

  public List<Timesheet> findAllTimesheets() {
    return timesheetRepo.findAll();
  }

  public Timesheet saveTimesheet(Timesheet timesheet) {
    return timesheetRepo.save(timesheet);
  }
}
