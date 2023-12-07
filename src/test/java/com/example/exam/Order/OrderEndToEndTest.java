package com.example.exam.Order;


import com.example.exam.Model.Customer;
import com.example.exam.Model.CustomerOrder;
import com.example.exam.Model.Subassembly;
import com.example.exam.Repo.CustomerRepository;
import com.example.exam.Repo.OrderRepository;
import com.example.exam.Repo.PartRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.json.JSONArray;
import org.junit.jupiter.api.Order;
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
public class OrderEndToEndTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void shouldFetchCustomerOrders() throws Exception{
        //Act
        MvcResult res = mockMvc.perform(get("/api/order"))
                .andExpect(status().isOk())
                .andReturn();

        String resString = res.getResponse().getContentAsString();
        JSONArray subassemblies = new JSONArray(resString);

        //Assert
        assertEquals(10, subassemblies.length());
    }

    @Test
    void shouldFetchOrderById() throws Exception{
        MvcResult res = mockMvc.perform(get("/api/order/1"))
                .andExpect(status().isOk())
                .andReturn();

        String resString = res.getResponse().getContentAsString();
        CustomerOrder customerOrder = objectMapper.readValue(resString, CustomerOrder.class);

        assertEquals(customerOrder.getOrderId(), 1);
        assertNotNull(customerOrder.getOrderDescription());
    }


    @Test
    void shouldCreateNewOrderWithExistingCustomer() throws Exception{

        CustomerOrder customerOrder = new CustomerOrder("Order name");
        String customerOrderJson = objectMapper.writeValueAsString(customerOrder);

        MvcResult res = mockMvc.perform(post("/api/order")
                        .contentType("application/json")
                        .content(customerOrderJson))
                .andExpect(status().isCreated())
                .andReturn();

        String resString = res.getResponse().getContentAsString();
        CustomerOrder addedCustomerOrder = objectMapper.readValue(resString, CustomerOrder.class);

        assertEquals(addedCustomerOrder.getOrderDescription(), customerOrder.getOrderDescription());
        assertNotNull(addedCustomerOrder.getOrderId());

    }


}
