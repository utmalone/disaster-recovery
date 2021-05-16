package com.disasterrecovery.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Machine {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String machineCode;
  private String description;
  private double hourlyRent;
  private double maxHoursPerDay;

  public Machine() {

  }

  public Machine(String machineCode, String description, double hourlyRent,
                 double maxHoursPerDay) {

    this.machineCode = machineCode;
    this.description = description;
    this.hourlyRent = hourlyRent;
    this.maxHoursPerDay = maxHoursPerDay;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMachineCode() {
    return machineCode;
  }

  public void setMachineCode(String machineCode) {
    this.machineCode = machineCode;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getHourlyRent() {
    return hourlyRent;
  }

  public void setHourlyRent(double hourlyRent) {
    this.hourlyRent = hourlyRent;
  }

  public double getMaxHoursPerDay() {
    return maxHoursPerDay;
  }

  public void setMaxHoursPerDay(double maxHoursPerDay) {
    this.maxHoursPerDay = maxHoursPerDay;
  }

  @Override
  public String toString() {
    return "Machine `" + machineCode + "` rents out at $" + hourlyRent +
      " per hour and can work up to " + maxHoursPerDay + " hours per day.";
  }
}

