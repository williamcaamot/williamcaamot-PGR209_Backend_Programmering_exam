package com.example.exam.Controller;

import com.example.exam.Model.Address;
import com.example.exam.Model.Customer;
import com.example.exam.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    /*@GetMapping("")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    } */

    @GetMapping("")
    public ResponseEntity<List<Customer>> getCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        Page<Customer> pageResult = customerService.getPaginatedCustomers(page, size);

        if (!pageResult.isEmpty()){
            return new ResponseEntity<>(pageResult.getContent(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
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
    }

    @PutMapping("")
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }
}
