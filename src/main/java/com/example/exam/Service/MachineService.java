package com.example.exam.Service;

import com.example.exam.Model.Machine;
import com.example.exam.Repo.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
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


    public Machine getAddressById(Long id){
        return machineRepository.findById(id).orElse(null);
    }
    public List<Machine> getAddress(){
        return machineRepository.findAll();
    }

    public Machine addAddress(Machine machine){
        return machineRepository.save(machine);
    }

    public void deleteAddress(Machine address){
        machineRepository.delete(address);
    }
    public Machine updateAddress(Machine machine){
        return machineRepository.save(machine);
    }


}
