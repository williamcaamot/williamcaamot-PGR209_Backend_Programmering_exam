package com.example.exam.Service;

import com.example.exam.Model.Order;
import com.example.exam.Repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public Order getOrderById(Long id){
        return orderRepository.findById(id).orElse(null);
    }
    public List<Order> getOrder(){
        return orderRepository.findAll();
    }

    public Order addOrder(Order order){
        return orderRepository.save(order);
    }

    public void deleteOrder(Order order){
        orderRepository.delete(order);
    }
    public Order updateOrder(Order order){
        return orderRepository.save(order);
    }



}
