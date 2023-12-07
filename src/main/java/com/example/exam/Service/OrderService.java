package com.example.exam.Service;

import com.example.exam.Model.Customer;
import com.example.exam.Model.CustomerOrder;
import com.example.exam.Model.Machine;
import com.example.exam.Repo.CustomerRepository;
import com.example.exam.Repo.MachineRepository;
import com.example.exam.Repo.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    OrderRepository orderRepository;
    CustomerRepository customerRepository;
    MachineRepository machineRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, MachineRepository machineRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.machineRepository = machineRepository;
    }

    public Page<CustomerOrder> getPaginatedOrders(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return orderRepository.findAll(pageRequest);
    }

    public CustomerOrder getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order with ID " + id + " could not be found!"));

    }

    public List<CustomerOrder> getOrder() {
        return orderRepository.findAll();
    }

    public CustomerOrder addOrder(CustomerOrder customerOrder) {
        return orderRepository.save(customerOrder);
    }

    public void deleteOrder(CustomerOrder customerOrder) {
        orderRepository.findById(customerOrder.getOrderId()).orElseThrow(() -> new EntityNotFoundException("Order with ID " + customerOrder.getOrderId() + " could not be found!"));
        orderRepository.delete(customerOrder);
    }

    public CustomerOrder updateOrder(CustomerOrder customerOrder) {
        orderRepository.findById(customerOrder.getOrderId()).orElseThrow(() -> new EntityNotFoundException("Order with ID " + customerOrder.getOrderId() + " could not be found!"));
        return orderRepository.save(customerOrder);
    }


    public CustomerOrder addCustomerToOrder(Long orderId, Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException("Customer with ID " + customerId + " could not be found!"));
        CustomerOrder customerOrder = orderRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException("Order with ID " + orderId + " could not be found!"));

        customerOrder.setCustomer(customer);
        customer.getCustomerOrders().add(customerOrder);

        return orderRepository.save(customerOrder);

    }


}
