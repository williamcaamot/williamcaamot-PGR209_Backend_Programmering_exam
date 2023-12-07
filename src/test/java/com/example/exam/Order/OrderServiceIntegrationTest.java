package com.example.exam.Order;

import com.example.exam.Model.CustomerOrder;
import com.example.exam.Repo.OrderRepository;
import com.example.exam.Service.OrderService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderServiceIntegrationTest {

    @Autowired
    private OrderService OrderService;

    @Autowired
    private OrderRepository OrderRepository;

    @Test
    @Transactional
    void shouldGetPaginatedOrders() {

        CustomerOrder aTestOrder = new CustomerOrder();
        OrderRepository.save(aTestOrder);

        Page<CustomerOrder> ordersPage = OrderService.getPaginatedOrders(0, 10);
        List<CustomerOrder> orders = ordersPage.getContent();

        assertNotNull(orders);
        assertFalse(orders.isEmpty());
    }

    @Test
    @Transactional
    void shouldFetchOrders(){

        CustomerOrder aNewOrder = new CustomerOrder();
        CustomerOrder savedOrder = OrderService.addOrder(aNewOrder);

        CustomerOrder foundOrder = OrderService.getOrderById(savedOrder.getOrderId());


        assertNotNull(foundOrder);
        assertEquals(savedOrder.getOrderId(), foundOrder.getOrderId());


    }
}
