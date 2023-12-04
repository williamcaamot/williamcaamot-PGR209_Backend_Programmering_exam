package com.example.exam.Controller;

import com.example.exam.Model.Customer;
import com.example.exam.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public Customer getCustomerById(@PathVariable Long id) throws Exception {
        return customerService.getCustomerById(id);
    }

    @PostMapping("")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer addedCustomer = customerService.addCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedCustomer);
    }

    @PostMapping("/{customerId}/addresses/{addressId}")
    public ResponseEntity<Customer> addAddressToCustomer(@PathVariable Long customerId, @PathVariable Long addressId) {
        Customer updatedCustomer = customerService.addAddressToCustomer(customerId, addressId);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedCustomer);
    }

    @DeleteMapping("")
    public void deleteCustomer(@RequestBody Customer customer) {
        customerService.deleteCustomer(customer);
        return;
    }

    @PutMapping("")
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }
}
