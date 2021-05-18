package com.disasterrecovery.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.disasterrecovery.model.Jobs;
import com.disasterrecovery.model.Timesheet;
import com.disasterrecovery.service.JobsService;
import com.disasterrecovery.service.TimesheetService;;



@Controller
public class MainController {
	
	  @Autowired
	  JobsService jobsService;

	  @Autowired
	  TimesheetService timesheetService;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/")
	public String home() {
		return "job_mgmt";
	}
	
	  @GetMapping("/api/jobs")
	  public ResponseEntity<List<Jobs>> listJobsAPI() {
	    try {
	      List<Jobs> jobs = jobsService.findAllJobs();

	      HttpHeaders headers = new HttpHeaders();
	      headers.add("Access-Control-Allow-Origin", "*");

	      return new ResponseEntity<>(jobs, headers, HttpStatus.OK);
	    } catch (NoSuchElementException e) {
	      e.printStackTrace();
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	  @CrossOrigin
	  @PostMapping("/api/add_timesheet")
	  public void addTimesheet(@RequestBody Timesheet timesheet) {

	    timesheetService.saveTimesheet(timesheet);
	  }
}
