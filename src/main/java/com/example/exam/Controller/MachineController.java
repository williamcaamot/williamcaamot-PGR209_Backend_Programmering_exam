package com.example.exam.Controller;

import com.example.exam.Model.Address;
import com.example.exam.Model.Machine;
import com.example.exam.Service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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


    /*@GetMapping("")
    public List<Machine> getMachines() {
        return machineService.getMachine();
    } */

    @GetMapping("")
    public ResponseEntity<List<Machine>> getMachine(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        Page<Machine> pageResult = machineService.getPaginatedMachines(page, size);

        if (!pageResult.isEmpty()){
            return new ResponseEntity<>(pageResult.getContent(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }



    @GetMapping("/{id}")
    public Machine getMachineById(@PathVariable Long id) throws Exception {
        return machineService.getMachineById(id);
    }

    @PostMapping("")
    public ResponseEntity<Machine> addMachine(@RequestBody Machine customer) {
        Machine addedMachine = machineService.addMachine(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedMachine);
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
