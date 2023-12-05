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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    }

    //Add part to subassembly

    //Delete subassembly

    //Wrap in try catch and expect it to throw and exception? Does the delete method even check if it already exists? Then if not that should be implemented

    //Update subassembly


}
