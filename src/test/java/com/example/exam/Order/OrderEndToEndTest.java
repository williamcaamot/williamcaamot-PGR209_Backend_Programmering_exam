package com.example.exam.Order;


import com.example.exam.Model.Customer;
import com.example.exam.Model.CustomerOrder;
import com.example.exam.Model.Machine;
import com.example.exam.Model.Subassembly;
import com.example.exam.Repo.CustomerRepository;
import com.example.exam.Repo.OrderRepository;
import com.example.exam.Repo.PartRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.json.JSONArray;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
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
        System.out.println(subassemblies.length());
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
    void shouldCreateNewOrder() throws Exception{

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


    @Test
    void shouldAddCustomerToOrder() throws Exception{
        //This tests use exisiting data in the database from the command line builder

        CustomerOrder customerOrder;
        Customer customer;

        //ACT
        mockMvc.perform(post("/api/order/1/customer/1"))
                .andExpect(status().isCreated());

        MvcResult orderRes = mockMvc.perform(get("/api/order/1"))
                .andExpect(status().isOk())
                .andReturn();

        MvcResult customerRes = mockMvc.perform(get("/api/customer/1"))
                .andExpect(status().isOk())
                .andReturn();

        String orderJson = orderRes.getResponse().getContentAsString();
        String customerJson = customerRes.getResponse().getContentAsString();

        customerOrder = objectMapper.readValue(orderJson, CustomerOrder.class);
        customer = objectMapper.readValue(customerJson, Customer.class);


        //Assert
        assertEquals(customer.getCustomerOrders().get(0).getOrderId(), customerOrder.getOrderId());
        assertEquals(customer.getCustomerOrders().get(0).getOrderDescription(), customerOrder.getOrderDescription());
    }

    @Test
    void shouldAddMachineToOrder() throws Exception{
        CustomerOrder customerOrder;
        Machine machine;

        mockMvc.perform(post("/api/order/1/machine/1"))
                .andExpect(status().isCreated());

        MvcResult orderRes = mockMvc.perform(get("/api/order/1"))
                .andExpect(status().isOk())
                .andReturn();

        MvcResult machineRes = mockMvc.perform(get("/api/machine/1"))
                .andExpect(status().isOk())
                .andReturn();

        String orderJson = orderRes.getResponse().getContentAsString();
        String machineJson = machineRes.getResponse().getContentAsString();

        customerOrder = objectMapper.readValue(orderJson, CustomerOrder.class);
        machine = objectMapper.readValue(machineJson, Machine.class);


        assertEquals(customerOrder.getMachines().get(0).getMachineId(), machine.getMachineId());
        assertEquals(customerOrder.getMachines().get(0).getMachineName(), machine.getMachineName());
        assertEquals(customerOrder.getMachines().get(0).getMachineDescription(), machine.getMachineDescription());


    }

    @Test
    void shouldCreateOrderThenDeleteIt() throws Exception{
//TODO COMPLETE THIS
    }

    @Test
    void shouldTryDeleteNonExistingOrder() throws Exception{
        CustomerOrder customerOrder = new CustomerOrder("Order name");
        customerOrder.setOrderId(213412L);
        String customerOrderJson = objectMapper.writeValueAsString(customerOrder);

        MvcResult res = mockMvc.perform(delete("/api/order")
                        .contentType("application/json")
                        .content(customerOrderJson))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void shouldCreateOrderThenUpdateIt() throws Exception{
//TODO COMPLETE THIS
    }





}
