package com.example.exam.Controller;

import com.example.exam.Model.Machine;
import com.example.exam.Service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/machine")
public class MachineController {


    MachineService machineService;

    @Autowired
    public MachineController(MachineService machineService) {
        this.machineService = machineService;
    }


    @GetMapping("")
    public List<Machine> getMachines() {
        return machineService.getMachine();
    }

    @GetMapping("/{id}")
    public Machine getMachineById(@PathVariable Long id) throws Exception {
        return machineService.getMachineById(id);
    }

    @PostMapping("")
    public Machine addMachine(@RequestBody Machine customer) {
        return machineService.addMachine(customer);
    }

    @DeleteMapping("")
    public void deleteMachine(@RequestBody Machine machine) {
        machineService.deleteMachine(machine);
        return;
    }

    @PutMapping("")
    public Machine updateMachine(@RequestBody Machine machine) {
        return machineService.updateMachine(machine);
    }
}
