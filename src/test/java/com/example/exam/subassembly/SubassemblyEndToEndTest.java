package com.example.exam.subassembly;


import com.example.exam.Model.Subassembly;
import com.example.exam.Repo.SubassemblyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SubassemblyEndToEndTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SubassemblyRepository subassemblyRepository;

    @Test
    void shouldFetchSubassemblies() throws Exception {

        //Act
        MvcResult res = mockMvc.perform(get("/api/subassembly"))
                .andExpect(status().isOk())
                .andReturn();

        String resString = res.getResponse().getContentAsString();
        JSONArray subassemblies = new JSONArray(resString);


        //Assert
        assertEquals(10, subassemblies.length());
    }


    @Test
    void shouldFetchSubassemblyById() throws Exception {
        MvcResult res = mockMvc.perform(get("/api/subassembly/1"))
                .andExpect(status().isOk())
                .andReturn();

        String resString = res.getResponse().getContentAsString();
        Subassembly subassembly = objectMapper.readValue(resString, Subassembly.class);

        assertEquals(subassembly.getSubassemblyId(), 1);
        assertNotNull(subassembly.getSubassemblyName());
        assertNotNull(subassembly.getSubassemblyDescription());

    }

    //Create new subassembly
    @Test
    void shouldCreateNewSubassembly() throws Exception {
        Subassembly subassembly = new Subassembly("Subassembly Name", "Subassembly Description");
        String subassemblyJson = objectMapper.writeValueAsString(subassembly);

        MvcResult res = mockMvc.perform(post("/api/subassembly")
                        .contentType("application/json")
                        .content(subassemblyJson))
                .andExpect(status().isCreated())
                .andReturn();

        String resString = res.getResponse().getContentAsString();
        Subassembly addedSubassembly = objectMapper.readValue(resString, Subassembly.class);

        assertEquals(subassembly.getSubassemblyDescription(), addedSubassembly.getSubassemblyDescription());
        assertEquals(subassembly.getSubassemblyName(), addedSubassembly.getSubassemblyName());


        //Cleanup
        subassemblyRepository.delete(addedSubassembly);

    }

    //Add part to subassembly
    @Test
    void shouldAddPartToSubassembly() throws Exception{
        MvcResult res = mockMvc.perform(post("/api/subassembly/1/part/1"))
                .andExpect(status().isCreated())
                .andReturn();

        String resString = res.getResponse().getContentAsString();
        Subassembly updatedSubassembly = objectMapper.readValue(resString, Subassembly.class);


        assertNotNull(updatedSubassembly.getSubassemblyId());
        assertNotNull(updatedSubassembly.getSubassemblyName());
        assertNotNull(updatedSubassembly.getSubassemblyDescription());
        assertNotNull(updatedSubassembly.getParts());
        assertNotNull(updatedSubassembly.getParts().get(0).getPartId());
        assertNotNull(updatedSubassembly.getParts().get(0).getPartName());
        assertNotNull(updatedSubassembly.getParts().get(0).getPartDescription());
    }

    //Delete subassembly
    @Test
    void shouldAddSubassemblyThenDeleteIt() throws Exception{

        //First adding a subassembly
        Subassembly subassembly = new Subassembly("Subassembly Name", "Subassembly Description");
        String subassemblyJson = objectMapper.writeValueAsString(subassembly);

        MvcResult addedRes = mockMvc.perform(post("/api/subassembly")
                        .contentType("application/json")
                        .content(subassemblyJson))
                .andExpect(status().isCreated())
                .andReturn();

        String addedResString = addedRes.getResponse().getContentAsString();
        Subassembly addedSubassembly = objectMapper.readValue(addedResString, Subassembly.class);
        String subassemblyToDeleteJson = objectMapper.writeValueAsString(addedSubassembly);


        //Deleting the subassembly added
        MvcResult deletedRes = mockMvc.perform(delete("/api/subassembly")
                        .contentType("application/json")
                        .content(subassemblyToDeleteJson))
                .andExpect(status().isOk())
                .andReturn();


        //Trying to get the subassembly that was removed by ID, should return 404 not found
        MvcResult res = mockMvc.perform(get("/api/subassembly/" + addedSubassembly.getSubassemblyId()))
                .andExpect(status().isNotFound())
                .andReturn();



    }


    //Update subassembly
    @Test
    void shouldAddSubassemblyThenUpdateIt() throws Exception{
        //First adding a subassembly
        Subassembly subassembly = new Subassembly("Subassembly Name", "Subassembly Description");
        Subassembly updateSubassembly = new Subassembly("Updated subassembly name", "updated subassmebly description");
        String subassemblyJson = objectMapper.writeValueAsString(subassembly);


        MvcResult addedRes = mockMvc.perform(post("/api/subassembly")
                        .contentType("application/json")
                        .content(subassemblyJson))
                .andExpect(status().isCreated())
                .andReturn();


        String addedResJson = addedRes.getResponse().getContentAsString();
        Subassembly addedSubassembly = objectMapper.readValue(addedResJson, Subassembly.class);
        updateSubassembly.setSubassemblyId(addedSubassembly.getSubassemblyId());
        String updateSubassemblyJson = objectMapper.writeValueAsString(updateSubassembly);

        MvcResult updatedRes = mockMvc.perform(put("/api/subassembly")
                .contentType("application/json")
                .content(updateSubassemblyJson))
                .andExpect(status().isOk())
                .andReturn();
    }

}
