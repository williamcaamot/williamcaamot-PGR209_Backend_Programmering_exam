package com.example.exam.Service;

import com.example.exam.Model.Address;
import com.example.exam.Model.Customer;
import com.example.exam.Repo.AddressRepository;
import com.example.exam.Repo.CustomerRepository;
import com.example.exam.Repo.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    CustomerRepository customerRepository;
    AddressRepository addressRepository;
    OrderRepository orderRepository;
    @Autowired
    public CustomerService(CustomerRepository customerRepository, AddressRepository addressRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.orderRepository = orderRepository;
    }

    public Customer getCustomerById(Long id) throws Exception {
        return customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer with ID: " + id + " could not be found!"));
    }

    public Page<Customer> getPaginatedCustomers(int pageNumber, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return customerRepository.findAll(pageRequest);
    }

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    public Customer addCustomer(Customer customer){
        return customerRepository.save(customer);

    }



    @Transactional
    public void deleteCustomer(Customer customer){
        Customer foundCustomer = customerRepository.findById(customer.getCustomerId()).orElseThrow(() -> new EntityNotFoundException("Customer with ID: " + customer.getCustomerId() + " could not be found!"));

        if (foundCustomer.getCustomerOrders() != null) {
            foundCustomer.getCustomerOrders().forEach(customerOrder -> {
                orderRepository.delete(customerOrder);
            });
            foundCustomer.getCustomerOrders().clear();
        }
        customerRepository.delete(customer);
    }


    public Customer updateCustomer(Customer customer){
        customerRepository.findById(customer.getCustomerId()).orElseThrow(() -> new EntityNotFoundException("Customer with ID: " + customer.getCustomerId() + " could not be found!"));
        return customerRepository.save(customer);
    }



    public Customer addAddressToCustomer(Long customerId, Long addressId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException("Customer with ID: " + customerId + " could not be found!"));
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new EntityNotFoundException("Address with ID: " + addressId + " could not be found!"));
        customer.getAddresses().add(address);
        return customerRepository.save(customer);
    }
}
