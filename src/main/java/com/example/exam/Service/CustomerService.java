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
        return customerRepository.findById(id).orElse(null); //TODO can have custom exceptions here
    }
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }
    public Customer addCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Customer customer){
        customerRepository.delete(customer);
    }
    public Customer updateCustomer(Customer customer){
        return customerRepository.save(customer);
    }
}
