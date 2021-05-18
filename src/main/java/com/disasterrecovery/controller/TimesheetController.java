package com.disasterrecovery.controller;

import com.disasterrecovery.model.Timesheet;
import com.disasterrecovery.service.JobsService;
import com.disasterrecovery.service.TimesheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TimesheetController {

  @Autowired
  TimesheetService timesheetService;

  @Autowired
  JobsService jobsService;

  @RequestMapping("/add_timesheet")
  public String addTimesheet() {
    return "add_timesheet";
  }

  @RequestMapping("/time_card_approval")
  public String showTimesheetForAdmin(Model model) {
    List<Timesheet> timesheets = timesheetService.findAllTimesheets();
    model.addAttribute("timesheets", timesheets);

    return "time_card_approval";
  }

  @RequestMapping("/time_card_submission")
  public String showTimesheetForContractor(Model model) {
    List<Timesheet> timesheets = timesheetService.findAllTimesheets();
    model.addAttribute("timesheets", timesheets);

    return "time_card_submission";
  }
}
