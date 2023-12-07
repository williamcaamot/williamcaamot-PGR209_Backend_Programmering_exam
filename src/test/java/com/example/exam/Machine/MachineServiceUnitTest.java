package com.example.exam.Machine;

import com.example.exam.Model.Machine;
import com.example.exam.Repo.MachineRepository;
import com.example.exam.Service.MachineService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class MachineServiceUnitTest {

    @MockBean
    private MachineRepository MachineRepository;

    @Autowired
    private MachineService MachineService;

    @Test
    void shouldFetchMachinees(){
        List<Machine> MachineList = List.of(new Machine(), new Machine());
        when(MachineRepository.findAll()).thenReturn(MachineList);

        var machines = MachineService.getMachine();
        assert machines.size() == 2;
    }
}
