package com.disasterrecovery.controller;

import com.disasterrecovery.model.Machine;

import com.disasterrecovery.service.MachineService;
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
public class MachineController {

  @Autowired
  MachineService machineService;

  @RequestMapping("/machine_mgmt")
  public String listMachines(Model model) {
    List<Machine> machines = machineService.findAllMachines();
    model.addAttribute("machines", machines);
    return "machine_mgmt";
  }

  @RequestMapping("/machine_delete/{id}")
  public String deleteMachine(@PathVariable("id") Long id) {
    machineService.deleteMachineById(id);
    return "redirect:/machine_mgmt";
  }

  @RequestMapping("/machine_edit/{id}")
  public ModelAndView editMachine(@PathVariable("id") Long id) {
    ModelAndView mav = new ModelAndView("edit_machine");
    Machine machine = machineService.findMachineById(id);
    mav.addObject("machine", machine);
    return mav;
  }

  @RequestMapping(value = "/save_machine", method = RequestMethod.POST)
  public String saveMachine(@ModelAttribute("machine") Machine machine) {
    machineService.saveMachine(machine);
    return "redirect:/machine_mgmt";
  }

  @RequestMapping("/add_machine")
  public String addMachine(Model model) {
    Machine machine = new Machine();
    model.addAttribute("machine", machine);
    return "add_machine";
  }
}

