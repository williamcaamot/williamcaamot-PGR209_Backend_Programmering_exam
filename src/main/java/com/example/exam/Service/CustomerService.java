package com.example.exam.Service;

import com.example.exam.Model.Customer;
import com.example.exam.Repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class CustomerService {

    CustomerRepository customerRepository;


    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomerById(Long id) throws Exception {
        return customerRepository.findById(id).orElseThrow(() -> new Exception("Problem")); //TODO can have custom exceptions here
    }

    public List<Customer> getCustomers(Pageable pageable){
        return customerRepository.findAll();
    }


}
