package com.example.exam.part;


import com.example.exam.Model.Part;
import com.example.exam.Repo.PartRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PartEndToEndTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PartRepository partRepository;

    @Test
    void shouldFetchParts() throws Exception {
        mockMvc.perform(get("/api/part"))
                .andExpect(status().isOk())
                .andDo(result -> {
                    System.out.println(result.getResponse().getContentAsString());
                });
    }

    @Test
    void shouldFetchNonExistingPart() throws Exception {
        mockMvc.perform(get("/api/part/14287210"))
                .andExpect(status().isNotFound());
    }


    @Test
    void shouldAddNewPart() throws Exception {
        //Arrange
        Part part = new Part("M5 Bolt", "Used for bolting stuff together");
        String partJson = objectMapper.writeValueAsString(part);

        //Act
        MvcResult res = mockMvc.perform(post("/api/part")
                        .contentType("application/json")
                        .content(partJson))
                .andExpect(status().isCreated())
                .andReturn();

        String responseString = res.getResponse().getContentAsString();
        Part addedPart = objectMapper.readValue(responseString, Part.class);


        //Assert
        assert addedPart.getPartDescription().equals(part.getPartDescription());
        assert addedPart.getPartName().equals(part.getPartName());


        //Cleanup
        partRepository.delete(addedPart);
    }

    @Test
    void shouldAddNewPartAndUpdateIt() throws Exception {
        Part part = new Part("M5 Bolt", "Used for bolting stuff together");
        String partJson = objectMapper.writeValueAsString(part);

        Part updatePart = new Part("Updated Part Name", "Updated Part Description");

        MvcResult res = mockMvc.perform(post("/api/part")
                        .contentType("application/json")
                        .content(partJson))
                .andExpect(status().isCreated())
                .andReturn();

        String responseString = res.getResponse().getContentAsString();
        Part addedPart = objectMapper.readValue(responseString, Part.class);
        updatePart.setPartId(addedPart.getPartId());
        String updatePartJson = objectMapper.writeValueAsString(updatePart);

        MvcResult updateRes = mockMvc.perform(put("/api/part")
                        .contentType("application/json")
                        .content(updatePartJson))
                .andExpect(status().isOk())
                .andReturn();

        String updateResponseString = updateRes.getResponse().getContentAsString();
        Part updatedPart = objectMapper.readValue(updateResponseString, Part.class);

        assert addedPart.getPartDescription().equals(part.getPartDescription());
        assert addedPart.getPartName().equals(part.getPartName());

        assert updatedPart.getPartDescription().equals(updatePart.getPartDescription());
        assert updatedPart.getPartName().equals(updatePart.getPartName());

        //Cleanup
        partRepository.delete(updatedPart);
        partRepository.delete(addedPart);

    }

    @Test
    void shouldTryToUpdateNonExistingPartAndGetErrorMessage() throws Exception {
        Part part = new Part();
        part.setPartName("Partname");
        part.setPartDescription("Part description");
        part.setPartId(3424324L);

        String partJson = objectMapper.writeValueAsString(part);

        MvcResult res = mockMvc.perform(put("/api/part")
                        .contentType("application/json")
                        .content(partJson))
                .andExpect(status().isNotFound())
                .andReturn();

        String responseString = res.getResponse().getContentAsString();
        assert responseString.equals("Part with ID " + part.getPartId() + " could not be found!");


    }

    @Test
    void shouldAddPartThenDelete() throws Exception{
        Part part = new Part("Part Name","Part Description");

        String partJson = objectMapper.writeValueAsString(part);
        MvcResult res = mockMvc.perform(post("/api/part")
                        .contentType("application/json")
                        .content(partJson))
                .andExpect(status().isCreated())
                .andReturn();

        String responseString = res.getResponse().getContentAsString();
        Part addedPart = objectMapper.readValue(responseString, Part.class);

        assert part.getPartName().equals(addedPart.getPartName());
        assert part.getPartDescription().equals(addedPart.getPartDescription());


        MvcResult delRes = mockMvc.perform(delete("/api/part/" + addedPart.getPartId()))
                .andExpect(status().isNoContent())
                .andReturn();
    }


}
