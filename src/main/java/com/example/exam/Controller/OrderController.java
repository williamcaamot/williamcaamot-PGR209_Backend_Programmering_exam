package com.example.exam.Controller;

import com.example.exam.Model.CustomerOrder;
import com.example.exam.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<CustomerOrder> getOrders(){
        return orderService.getOrder();
    }

    @GetMapping("/{id}")
    public CustomerOrder getOrderById(@PathVariable Long id){
        return orderService.getOrderById(id);
    }

    @PostMapping("")
    public CustomerOrder addOrder(@RequestBody CustomerOrder customerOrder){
        return orderService.addOrder(customerOrder);
    }

    @DeleteMapping("")
    public void deleteOrder(@RequestBody CustomerOrder customerOrder){
        orderService.deleteOrder(customerOrder);
    }

    @PutMapping("")
    public CustomerOrder updateOrder(@RequestBody CustomerOrder customerOrder){
        return orderService.updateOrder(customerOrder);
    }



}
