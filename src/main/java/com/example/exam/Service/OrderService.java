package com.example.exam.Service;

import com.example.exam.Model.CustomerOrder;
import com.example.exam.Model.Machine;
import com.example.exam.Repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Page<CustomerOrder> getPaginatedOrders(int pageNumber, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return orderRepository.findAll(pageRequest);
    }

    public CustomerOrder getOrderById(Long id){
        return orderRepository.findById(id).orElse(null);
    }
    public List<CustomerOrder> getOrder(){
        return orderRepository.findAll();
    }

    public CustomerOrder addOrder(CustomerOrder customerOrder){
        return orderRepository.save(customerOrder);
    }

    public void deleteOrder(CustomerOrder customerOrder){
        orderRepository.delete(customerOrder);
    }
    public CustomerOrder updateOrder(CustomerOrder customerOrder){
        return orderRepository.save(customerOrder);
    }



}
