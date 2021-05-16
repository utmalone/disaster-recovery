package com.disasterrecovery.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Jobs {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String jobCode;
  private String description;
  private double hourlyRate;
  private double maxHoursPerDay;

  public Jobs() {

  }

  public Jobs(String jobCode, String description, double hourlyRate,
              double maxHoursPerDay) {

    this.jobCode = jobCode;
    this.description = description;
    this.hourlyRate = hourlyRate;
    this.maxHoursPerDay = maxHoursPerDay;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getJobCode() {
    return jobCode;
  }

  public void setJobCode(String jobCode) {
    this.jobCode = jobCode;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getHourlyRate() {
    return hourlyRate;
  }

  public void setHourlyRate(double hourlyRate) {
    this.hourlyRate = hourlyRate;
  }

  public double getMaxHoursPerDay() {
    return maxHoursPerDay;
  }

  public void setMaxHoursPerDay(double maxHoursPerDay) {
    this.maxHoursPerDay = maxHoursPerDay;
  }

  @Override
  public String toString() {
    return "Job `" + jobCode + "` has an hourly rate of $" + hourlyRate +
      " and can work up to " + maxHoursPerDay + " hrs/day.";
  }
}

