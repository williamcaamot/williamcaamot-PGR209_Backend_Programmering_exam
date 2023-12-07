package com.example.exam.Machine;

import com.example.exam.Model.Machine;
import com.example.exam.Repo.MachineRepository;
import com.example.exam.Service.MachineService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MachineServiceIntegrationTest {

    @Autowired
    private MachineService MachineService;

    @Autowired
    private MachineRepository MachineRepository;

    @Test
    @Transactional
    void shouldGetPaginatedMachines() {

        Machine aTestMachine = new Machine();
        MachineRepository.save(aTestMachine);

        Page<Machine> MachinesPage = MachineService.getPaginatedMachines(0, 10);
        List<Machine> machines = MachinesPage.getContent();

        assertNotNull(machines);
        assertFalse(machines.isEmpty());
    }

    @Test
    @Transactional
    void shouldFetchMachines(){

        Machine aNewMachine = new Machine();
        Machine savedMachine = MachineService.addMachine(aNewMachine);

        Machine foundMachine = MachineService.getMachineById(savedMachine.getMachineId());


        assertNotNull(foundMachine);
        assertEquals(savedMachine.getMachineId(), foundMachine.getMachineId());


    }
}
