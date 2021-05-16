package com.disasterrecovery.service;

import com.disasterrecovery.model.Machine;
import com.disasterrecovery.repository.MachineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineService {

  @Autowired
  MachineRepo machineRepo;

  public List<Machine> findAllMachines() {
    return machineRepo.findAll();
  }

  public Machine saveMachine(Machine machine) {
    return machineRepo.save(machine);
  }

  public void deleteMachineById(Long id) {
    machineRepo.deleteById(id);
  }

  public Machine findMachineById(Long id) {
    return machineRepo.findById(id).get();
  }
}
