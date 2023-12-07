package com.example.exam.Service;

import com.example.exam.Model.Customer;
import com.example.exam.Model.Machine;
import com.example.exam.Model.Subassembly;
import com.example.exam.Repo.MachineRepository;
import com.example.exam.Repo.SubassemblyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import java.util.List;

@Service
public class MachineService {


    MachineRepository machineRepository;
    SubassemblyRepository subassemblyRepository;

    @Autowired
    public MachineService(MachineRepository machineRepository, SubassemblyRepository subassemblyRepository) {
        this.machineRepository = machineRepository;
        this.subassemblyRepository = subassemblyRepository;
    }

    public Page<Machine> getPaginatedMachines(int pageNumber, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return machineRepository.findAll(pageRequest);
    }

    public Machine getMachineById(Long id) {
        return machineRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Machine with ID " + id + " could not be found!"));

    }
    public List<Machine> getMachine(){
        return machineRepository.findAll();
    }

    public Machine addMachine(Machine machine){
        return machineRepository.save(machine);
    }

    public void deleteMachine(Machine machine){
        machineRepository.findById(machine.getMachineId()).orElseThrow(() -> new EntityNotFoundException("Machine with ID " + machine.getMachineId() + " could not be found!"));
        machineRepository.delete(machine);
    }
    public Machine updateMachine(Machine machine){
        machineRepository.findById(machine.getMachineId()).orElseThrow(() -> new EntityNotFoundException("Machine with ID " + machine.getMachineId() + " could not be found!"));
        return machineRepository.save(machine);
    }


    public Machine addSubassemblyToMachine(Long machineId, Long subassemblyId){
        Machine machine = machineRepository.findById(machineId).orElseThrow(() -> new EntityNotFoundException("Machine with ID " + machineId + " could not be found!"));
        Subassembly subassembly = subassemblyRepository.findById(subassemblyId).orElseThrow(() -> new EntityNotFoundException("Subassembly with ID " + subassemblyId + " could not be found!"));
        machine.getSubassemblies().add(subassembly);
        return machineRepository.save(machine);
    }



}
