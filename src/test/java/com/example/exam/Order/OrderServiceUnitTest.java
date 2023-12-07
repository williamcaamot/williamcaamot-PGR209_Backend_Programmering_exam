package com.example.exam.Order;

import com.example.exam.Model.CustomerOrder;
import com.example.exam.Repo.OrderRepository;
import com.example.exam.Service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OrderServiceUnitTest {

    @MockBean
    private OrderRepository OrderRepository;

    @Autowired
    private OrderService OrderService;

    @Test
    void shouldFetchOrders(){
        List<CustomerOrder> OrderList = List.of(new CustomerOrder(), new CustomerOrder());
        when(OrderRepository.findAll()).thenReturn(OrderList);

        var orders = OrderService.getOrder();
        assert orders.size() == 2;
    }

    @Test
    public void getCustomerOrderById(){
        Long exampleId = 1L;
        CustomerOrder mockCustomerOrder = new CustomerOrder();

        mockCustomerOrder.setOrderId(exampleId);

        when(OrderRepository.findById(exampleId)).thenReturn(Optional.of(mockCustomerOrder));

        CustomerOrder found = OrderService.getOrderById(exampleId);

        assertNotNull(found);
        assertEquals(exampleId, found.getOrderId());

        verify(OrderRepository).findById(exampleId);
    }
}
