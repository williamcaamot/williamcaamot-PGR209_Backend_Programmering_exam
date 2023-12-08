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

import javax.crypto.Mac;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Test
    void shouldAddSubassemblyToMachine() throws Exception {
        MvcResult res = mockMvc.perform(post("/api/machine/1/subassembly/1"))
                .andExpect(status().isCreated())
                .andReturn();

        String resString = res.getResponse().getContentAsString();
        Machine machine = objectMapper.readValue(resString, Machine.class);

        assertNotNull(machine.getMachineId());
        assertNotNull(machine.getMachineName());
        assertNotNull(machine.getMachineDescription());
        assertNotNull(machine.getSubassemblies());
        assertNotNull(machine.getSubassemblies().get(0));
        assertNotNull(machine.getSubassemblies().get(0).getSubassemblyId());
        assertNotNull(machine.getSubassemblies().get(0).getSubassemblyName());
        assertNotNull(machine.getSubassemblies().get(0).getSubassemblyDescription());


    }


    //This test will also delete the machine afterwards, therefore it tests that as well:
    @Test
    void shouldAddMachineThenUpdateIt() throws Exception {
        //Add machine first
        Machine machineToAdd = new Machine("Cool Machine", "A machine that cools you down");
        String machineToAddJson = objectMapper.writeValueAsString(machineToAdd);

        MvcResult addedRes = mockMvc.perform(post("/api/machine")
                        .contentType("application/json")
                        .content(machineToAddJson))
                .andExpect(status().isCreated())
                .andReturn();

        String addedResString = addedRes.getResponse().getContentAsString();
        Machine addedMachine = objectMapper.readValue(addedResString, Machine.class);

        //Update machine
        Machine machineToUpdate = new Machine("Hot Machine", "A machine that heats you up");
        machineToUpdate.setMachineId(addedMachine.getMachineId());
        String machineToUpdateJson = objectMapper.writeValueAsString(machineToUpdate);

        MvcResult updatedRes = mockMvc.perform(put("/api/machine")
                .contentType("application/json")
                        .content(machineToUpdateJson))
                .andExpect(status().isOk())
                .andReturn();

        String udpatedResString = updatedRes.getResponse().getContentAsString();
        Machine updatedMachine = objectMapper.readValue(udpatedResString, Machine.class);

        assertEquals(updatedMachine.getMachineId(), addedMachine.getMachineId());
        assertEquals(machineToUpdate.getMachineName(), updatedMachine.getMachineName());
        assertEquals(machineToUpdate.getMachineDescription(), updatedMachine.getMachineDescription());

    }


}
