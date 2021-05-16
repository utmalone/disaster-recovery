package com.disasterrecovery.service;

import com.disasterrecovery.model.Jobs;
import com.disasterrecovery.repository.JobsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobsService {

  @Autowired
  JobsRepo jobsRepo;

  public List<Jobs> findAllJobs() {
    return jobsRepo.findAll();
  }

  public Jobs saveJob(Jobs job) {
    return jobsRepo.save(job);
  }

  public void deleteJobById(Long id) {
    jobsRepo.deleteById(id);
  }

  public Jobs findJobById(Long id) {
    return jobsRepo.findById(id).get();
  }
}

