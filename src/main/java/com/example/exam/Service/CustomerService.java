package com.example.exam.Service;

import com.example.exam.Model.Address;
import com.example.exam.Model.Customer;
import com.example.exam.Repo.AddressRepository;
import com.example.exam.Repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    CustomerRepository customerRepository;
    AddressRepository addressRepository;
    @Autowired
    public CustomerService(CustomerRepository customerRepository, AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
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

    public Customer addAddressToCustomer(Long customerId, Long addressId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        Address address = addressRepository.findById(addressId).orElse(null);
        customer.getAddresses().add(address);
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Customer customer){
        customerRepository.delete(customer);
    }
    public Customer updateCustomer(Customer customer){
        return customerRepository.save(customer);
    }
}
