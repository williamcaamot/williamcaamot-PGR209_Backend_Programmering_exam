package com.example.exam.Machine;

import com.example.exam.Model.Machine;
import com.example.exam.Model.Machine;
import com.example.exam.Repo.MachineRepository;
import com.example.exam.Service.MachineService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
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

    @Test
    public void getMachineById(){
        Long exampleId = 1L;
        Machine mockMachine = new Machine();

        mockMachine.setMachineId(exampleId);

        when(MachineRepository.findById(exampleId)).thenReturn(Optional.of(mockMachine));

        Machine found = MachineService.getMachineById(exampleId);

        assertNotNull(found);
        assertEquals(exampleId, found.getMachineId());

        verify(MachineRepository).findById(exampleId);
    }
}
