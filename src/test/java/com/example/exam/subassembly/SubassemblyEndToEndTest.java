package com.example.exam.subassembly;


import com.example.exam.Repo.SubassemblyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    //Get all subassemblies
    @Test
    void shouldFetchSubassemblies() throws Exception{
        mockMvc.perform(get("/api/subassembly"))
                .andExpect(status().isOk());
    }

    //Get subassembly by id

    //Create new subassembly

    //Add part to subassembly

    //Delete subassembly

    //Update subassembly


}
