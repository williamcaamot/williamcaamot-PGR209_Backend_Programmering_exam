package com.example.exam.part;


import com.example.exam.Model.Part;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PartEndToEndTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldFetchParts() throws Exception{
        mockMvc.perform(get("/api/part"))
                .andExpect(status().isOk())
                .andDo(result -> {
                    System.out.println(result.getResponse().getContentAsString());
                });
    }

    @Test
    void shouldAddNewPart() throws Exception{
        Part part = new Part("M5 Bolt", "Used for bolting stuff together");
        String partJson = objectMapper.writeValueAsString(part);

        MvcResult res = mockMvc.perform(post("/api/part")
                        .contentType("application/json")
                        .content(partJson))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        String responseString = res.getResponse().getContentAsString();
        Part addedPart = objectMapper.readValue(responseString, Part.class);


        assert addedPart.getPartDescription().equals(part.getPartDescription());
        assert addedPart.getPartName().equals(part.getPartName());
    }



}
