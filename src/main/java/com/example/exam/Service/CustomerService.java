package com.example.exam.Service;

import com.example.exam.Model.Address;
import com.example.exam.Model.Customer;
import com.example.exam.Repo.AddressRepository;
import com.example.exam.Repo.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public Page<Customer> getPaginatedCustomers(int pageNumber, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return customerRepository.findAll(pageRequest);
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
        customerRepository.findById(customer.getCustomerId()).orElseThrow(() -> new EntityNotFoundException("Customer with ID: " + customer.getCustomerId() + " could not be found!"));
        return customerRepository.save(customer);
    }
}
