package com.disasterrecovery.controller;

import com.disasterrecovery.model.Jobs;

import com.disasterrecovery.service.JobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class JobController {

  @Autowired
  JobsService jobsService;

  @RequestMapping("/job_mgmt")
  public String listJobs(Model model) {
    List<Jobs> jobs = jobsService.findAllJobs();
    model.addAttribute("jobs", jobs);

    return "job_mgmt";
  }

  @RequestMapping("/job_delete/{id}")
  public String deleteJob(@PathVariable("id") Long id) {
    jobsService.deleteJobById(id);
    return "redirect:/job_mgmt";
  }

  @RequestMapping("/job_edit/{id}")
  public ModelAndView editJob(@PathVariable("id") Long id) {
    ModelAndView mav = new ModelAndView("edit_job");
    Jobs job = jobsService.findJobById(id);
    mav.addObject("job", job);
    return mav;
  }

  @RequestMapping(value = "/save_job", method = RequestMethod.POST)
  public String saveJob(@ModelAttribute("job") Jobs job) {
    jobsService.saveJob(job);
    return "redirect:/job_mgmt";
  }

  @RequestMapping("/add_job")
  public String createNewJob(Model model) {
    Jobs job = new Jobs();
    model.addAttribute("job", job);
    return "add_job";
  }
}
