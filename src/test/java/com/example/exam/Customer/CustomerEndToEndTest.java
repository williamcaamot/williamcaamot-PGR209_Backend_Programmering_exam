package com.example.exam.Customer;

import com.example.exam.Model.Customer;
import com.example.exam.Model.CustomerOrder;
import com.example.exam.Model.Machine;
import com.example.exam.Repo.CustomerRepository;
import com.example.exam.Repo.MachineRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CustomerEndToEndTest {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerRepository customerRepository;


    @Test
    void shouldFetchAllCustomers() throws Exception {

        //Act
        MvcResult res = mockMvc.perform(get("/api/customer"))
                .andExpect(status().isOk())
                .andReturn();

        String resString = res.getResponse().getContentAsString();
        JSONArray machines = new JSONArray(resString);


        //Assert
        assertEquals(10, machines.length());

    }


    @Test
    void shouldFetchCustomerById() throws Exception {
        MvcResult res = mockMvc.perform(get("/api/customer/1"))
                .andExpect(status().isOk())
                .andReturn();

        String resString = res.getResponse().getContentAsString();
        Customer customer = objectMapper.readValue(resString, Customer.class);

        assertEquals(customer.getCustomerId(), 1);
        assertNotNull(customer.getCustomerEmail());
        assertNotNull(customer.getCustomerName());

    }

    @Test
    void shouldTryFetchNonExistingCustomer() throws Exception{
        MvcResult res = mockMvc.perform(get("/api/customer/21312"))
                .andExpect(status().isNotFound())
                .andReturn();
    }

}
