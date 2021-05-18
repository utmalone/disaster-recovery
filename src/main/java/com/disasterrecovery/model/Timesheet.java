package com.disasterrecovery.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Timesheet {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String jobSiteCode;
  private String contractorName;
  private Date date;
  private double hoursWorked;
  private double totalEarned;
  private double hoursRented;
  private double totalRent;
  private boolean status = false;

  public Timesheet() {

  }

  public Timesheet(String jobSiteCode, String contractorName, Date date,
                   double hoursWorked, double totalEarned,
                   double hoursRented, double totalRent, boolean status) {

    this.jobSiteCode = jobSiteCode;
    this.contractorName = contractorName;
    this.date = date;
    this.hoursWorked = hoursWorked;
    this.totalEarned = totalEarned;
    this.hoursRented = hoursRented;
    this.totalRent = totalRent;
    this.status = status;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getJobSiteCode() {
    return jobSiteCode;
  }

  public void setJobSiteCode(String jobSiteCode) {
    this.jobSiteCode = jobSiteCode;
  }

  public String getContractorName() {
    return contractorName;
  }

  public void setContractorName(String contractorName) {
    this.contractorName = contractorName;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public double getHoursWorked() {
    return hoursWorked;
  }

  public void setHoursWorked(double hoursWorked) {
    this.hoursWorked = hoursWorked;
  }

  public double getTotalEarned() {
    return totalEarned;
  }

  public void setTotalEarned(double totalEarned) {
    this.totalEarned = totalEarned;
  }

  public double getHoursRented() {
    return hoursRented;
  }

  public void setHoursRented(double hoursRented) {
    this.hoursRented = hoursRented;
  }

  public double getTotalRent() {
    return totalRent;
  }

  public void setTotalRent(double totalRent) {
    this.totalRent = totalRent;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "Timesheet{" +
      "id=" + id +
      ", jobSiteCode='" + jobSiteCode + '\'' +
      ", contractorName='" + contractorName + '\'' +
      ", date=" + date +
      ", hoursWorked=" + hoursWorked +
      ", totalEarned=" + totalEarned +
      ", hoursRented=" + hoursRented +
      ", totalRent=" + totalRent +
      '}';
  }
}
