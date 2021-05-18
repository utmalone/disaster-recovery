package com.disasterrecovery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.disasterrecovery.model.Timesheet;

@Repository
public interface TimesheetRepo extends JpaRepository<Timesheet, Long> {
}
