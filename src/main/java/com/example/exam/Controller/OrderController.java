package com.example.exam.Controller;

import com.example.exam.Model.Order;
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
    public List<Order> getOrders(){
        return orderService.getOrder();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id){
        return orderService.getOrderById(id);
    }

    @PostMapping("")
    public Order addOrder(@RequestBody Order order){
        return orderService.addOrder(order);
    }

    @DeleteMapping("")
    public void deleteOrder(@RequestBody Order order){
        orderService.deleteOrder(order);
    }

    @PutMapping("")
    public Order updateOrder(@RequestBody Order order){
        return orderService.updateOrder(order);
    }



}
