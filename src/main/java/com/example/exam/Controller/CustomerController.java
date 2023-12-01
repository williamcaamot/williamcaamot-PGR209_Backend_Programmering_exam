package com.example.exam.Controller;

import com.example.exam.Model.Customer;
import com.example.exam.Service.CustomerService;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        try {
            return customerService.getCustomerById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("")
    public Customer addCustomer(@RequestBody Customer customer){
        return customerService.addCustomer(customer);
    }
    @DeleteMapping("")
    public void deleteCustomer(@RequestBody Customer customer){
        customerService.deleteCustomer(customer);
        return;
    }
    @PutMapping("")
    public Customer updateCustomer(@RequestBody Customer customer){
        return  customerService.updateCustomer(customer);
    }
}
