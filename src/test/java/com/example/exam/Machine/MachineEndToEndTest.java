package com.example.exam.Machine;

import com.example.exam.Model.Machine;
import com.example.exam.Model.Subassembly;
import com.example.exam.Repo.MachineRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MachineEndToEndTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MachineRepository machineRepository;

    @Test
    void shouldFetchAllMachines() throws Exception {

        //Act
        MvcResult res = mockMvc.perform(get("/api/machine"))
                .andExpect(status().isOk())
                .andReturn();

        String resString = res.getResponse().getContentAsString();
        JSONArray machines = new JSONArray(resString);


        //Assert
        assertEquals(10, machines.length());

    }

    @Test
    void shouldFetchMachineById() throws Exception {
        MvcResult res = mockMvc.perform(get("/api/machine/1"))
                .andExpect(status().isOk())
                .andReturn();

        String resString = res.getResponse().getContentAsString();
        Machine machine = objectMapper.readValue(resString, Machine.class);

        assertEquals(machine.getMachineId(), 1);
        assertNotNull(machine.getMachineName());
        assertNotNull(machine.getMachineDescription());

    }

    @Test
    void shouldTryFetchNonExistingMachine() throws Exception {
        MvcResult res = mockMvc.perform(get("/api/machine/1123123"))
                .andExpect(status().isNotFound())
                .andReturn();

    }



    @Test
    void shouldCreateNewMachine() throws Exception {
        //Arrange
        Machine machine = new Machine("Machine name", "Machine description");
        String machineJson = objectMapper.writeValueAsString(machine);


        //Act

        MvcResult res = mockMvc.perform(post("/api/machine")
                        .contentType("application/json")
                        .content(machineJson))
                .andExpect(status().isCreated())
                .andReturn();

        String resString = res.getResponse().getContentAsString();
        Machine addedMachine = objectMapper.readValue(resString, Machine.class);

        //Assert
        assertEquals(addedMachine.getMachineDescription(), machine.getMachineDescription());
        assertEquals(addedMachine.getMachineName(), machine.getMachineName());
        assertNotNull(addedMachine.getMachineId());


        //Cleanup
        machineRepository.delete(addedMachine);
    }


    //TODO test to add subassembly to machine

    //TODO test for updating machine

    //TODO test for deleting machine


}
