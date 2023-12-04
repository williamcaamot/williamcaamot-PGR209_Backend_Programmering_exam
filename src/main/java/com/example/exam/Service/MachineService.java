package com.example.exam.Service;

import com.example.exam.Model.Customer;
import com.example.exam.Model.Machine;
import com.example.exam.Repo.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import java.util.List;

@Service
public class MachineService {


    MachineRepository machineRepository;

    @Autowired
    public MachineService(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    public Page<Machine> getPaginatedMachines(int pageNumber, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return machineRepository.findAll(pageRequest);
    }

    public Machine getMachineById(Long id){
        return machineRepository.findById(id).orElse(null);
    }
    public List<Machine> getMachine(){
        return machineRepository.findAll();
    }

    public Machine addMachine(Machine machine){
        return machineRepository.save(machine);
    }

    public void deleteMachine(Machine address){
        machineRepository.delete(address);
    }
    public Machine updateMachine(Machine machine){
        return machineRepository.save(machine);
    }


}
