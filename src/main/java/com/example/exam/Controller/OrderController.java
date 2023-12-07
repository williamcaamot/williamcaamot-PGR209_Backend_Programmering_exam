package com.example.exam.Controller;

import com.example.exam.Model.CustomerOrder;
import com.example.exam.Model.Machine;
import com.example.exam.Service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    /*@GetMapping
    public List<CustomerOrder> getOrders(){
        return orderService.getOrder();
    } */

    @GetMapping("")
    public ResponseEntity<List<CustomerOrder>> getOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        Page<CustomerOrder> pageResult = orderService.getPaginatedOrders(page, size);

        if (!pageResult.isEmpty()){
            return new ResponseEntity<>(pageResult.getContent(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public CustomerOrder getOrderById(@PathVariable Long id){
        return orderService.getOrderById(id);
    }

    @PostMapping("")
    public ResponseEntity<CustomerOrder> addOrder(@RequestBody CustomerOrder customerOrder){
        CustomerOrder addedCustomerOrder = orderService.addOrder(customerOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedCustomerOrder);
    }

    @DeleteMapping("")
    public void deleteOrder(@RequestBody CustomerOrder customerOrder){
        orderService.deleteOrder(customerOrder);
    }

    @PutMapping("")
    public CustomerOrder updateOrder(@RequestBody CustomerOrder customerOrder){
        return orderService.updateOrder(customerOrder);
    }


    @PostMapping("/{orderId}/customer/{customerId}")
    public ResponseEntity<CustomerOrder> addCustomerToOrder(@PathVariable Long orderId, @PathVariable Long customerId){
        CustomerOrder customerOrder = orderService.addCustomerToOrder(orderId, customerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerOrder);
    }



    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
