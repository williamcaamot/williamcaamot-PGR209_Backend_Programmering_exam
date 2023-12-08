package com.example.exam.Address;

import com.example.exam.Model.Address;
import com.example.exam.Repo.AddressRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AddressEndToEndTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    AddressRepository addressRepository;

    //This test tests for creating new/adding and getting by ID:
    @Test
    void shouldAddAddressThenGetItById() throws Exception {
        Address addressToAdd = new Address("Norway", "Oslo", "CoolStreet 43");
        String addressToAddAsJson = objectMapper.writeValueAsString(addressToAdd);

        MvcResult addedRes = mockMvc.perform(post("/api/address")
                        .contentType("application/json")
                        .content(addressToAddAsJson))
                .andExpect(status().isCreated())
                .andReturn();

        String addedResString = addedRes.getResponse().getContentAsString();
        Address addedAddress = objectMapper.readValue(addedResString, Address.class);

        MvcResult foundRes = mockMvc.perform(get("/api/address/" + addedAddress.getAddressId()))
                .andExpect(status().isOk())
                .andReturn();

        String foundResString = foundRes.getResponse().getContentAsString();
        Address foundAddress = objectMapper.readValue(foundResString, Address.class);

        assertEquals(foundAddress.getArea(), addressToAdd.getArea());
        assertEquals(foundAddress.getCountry(), addressToAdd.getCountry());
        assertEquals(foundAddress.getStreetAndNumber(), addressToAdd.getStreetAndNumber());
        assertNotNull(foundAddress.getAddressId());
        assertEquals(addressToAdd.getAddressId(), 0L);


        //Cleanup
        addressRepository.delete(foundAddress);
    }

    @Test
    void shouldTryFetchNonExistingAddress() throws Exception {
        MvcResult foundRes = mockMvc.perform(get("/api/address/12312312"))
                .andExpect(status().isNotFound())
                .andReturn();
    }


    //Get all
    @Test
    void shouldFetchAddresses() throws Exception {
        mockMvc.perform(get("/api/address"))
                .andExpect(status().isOk())
                .andDo(result -> {
                    System.out.println(result.getResponse().getContentAsString());
                });
    }



    //Delete
    @Test
    void shouldAddAddressThenDeleteIt() throws Exception {
        Address addressToAdd = new Address("Norway", "Oslo", "CoolStreet 43");
        String addressToAddAsJson = objectMapper.writeValueAsString(addressToAdd);

        MvcResult addedRes = mockMvc.perform(post("/api/address")
                        .contentType("application/json")
                        .content(addressToAddAsJson))
                .andExpect(status().isCreated())
                .andReturn();

        String addedResString = addedRes.getResponse().getContentAsString();
        Address addedAddress = objectMapper.readValue(addedResString, Address.class);
        String addedAddressAsJson = objectMapper.writeValueAsString(addedAddress);

        mockMvc.perform(delete("/api/address")
                        .contentType("application/json")
                        .content(addedAddressAsJson))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void shouldTryDeleteNonExistingAddress() throws Exception {
        Address addressToDelete = new Address("Norway", "Oslo", "CoolStreet 43");
        addressToDelete.setAddressId(3123L);
        String addressToDeleteAsJson = objectMapper.writeValueAsString(addressToDelete);

        MvcResult addedRes = mockMvc.perform(delete("/api/address")
                        .contentType("application/json")
                        .content(addressToDeleteAsJson))
                .andExpect(status().isNotFound())
                .andReturn();
    }


    //Update
    @Test
    void shouldAddNewAddressThenUpdateIt() throws Exception {

        //Add address
        Address addressToAdd = new Address("Norway", "Oslo", "CoolStreet 43");
        String addressToAddAsJson = objectMapper.writeValueAsString(addressToAdd);

        MvcResult addedRes = mockMvc.perform(post("/api/address")
                        .contentType("application/json")
                        .content(addressToAddAsJson))
                .andExpect(status().isCreated())
                .andReturn();

        String addedResString = addedRes.getResponse().getContentAsString();
        Address addedAddress = objectMapper.readValue(addedResString, Address.class);


        //Update address
        Address updatedAddress = new Address("Sweden", "Stockholm", "NotSoCoolStreet 34");
        updatedAddress.setAddressId(addedAddress.getAddressId());
        String updatedAddressAsJson = objectMapper.writeValueAsString(updatedAddress);

        MvcResult updatedRes = mockMvc.perform(put("/api/address")
                        .contentType("application/json")
                        .content(updatedAddressAsJson))
                .andExpect(status().isOk())
                .andReturn();

        String updatedResString = updatedRes.getResponse().getContentAsString();
        Address addressUpdated = objectMapper.readValue(updatedResString, Address.class);

        assertEquals(updatedAddress.getAddressId(), addedAddress.getAddressId());
        assertEquals(updatedAddress.getArea(), addressUpdated.getArea());
        assertEquals(updatedAddress.getCountry(), addressUpdated.getCountry());
        assertEquals(updatedAddress.getStreetAndNumber(), addressUpdated.getStreetAndNumber());

        //Cleanup
        addressRepository.delete(updatedAddress);
    }

    @Test
    void shouldTryUpdateNonExistingAddress() throws Exception {

        //Add address
        Address addressToUpdate = new Address("Norway", "Oslo", "CoolStreet 43");
        addressToUpdate.setAddressId(31212L);
        String addressToUpdateAsJson = objectMapper.writeValueAsString(addressToUpdate);

        MvcResult addedRes = mockMvc.perform(delete("/api/address")
                        .contentType("application/json")
                        .content(addressToUpdateAsJson))
                .andExpect(status().isNotFound())
                .andReturn();
    }

}
