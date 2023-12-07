package com.example.exam.Customer;

import com.example.exam.Model.Customer;
import com.example.exam.Repo.CustomerRepository;
import com.example.exam.Service.CustomerService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerServiceIntegrationTest {
    @Autowired
    private CustomerService CustomerService;

    @Autowired
    private CustomerRepository CustomerRepository;

    @Test
    @Transactional
    void shouldGetPaginatedCustomeres() {

        Customer aTestCustomer = new Customer();
        CustomerRepository.save(aTestCustomer);

        Page<Customer> CustomeresPage = CustomerService.getPaginatedCustomers(0, 10);
        List<Customer> customers = CustomeresPage.getContent();

        assertNotNull(customers);
        assertFalse(customers.isEmpty());
    }

    @Test
    @Transactional
    void shouldFetchCustomers() throws Exception {

        Customer aNewCustomer = new Customer();
        Customer savedCustomer = CustomerService.addCustomer(aNewCustomer);

        Customer foundCustomer = CustomerService.getCustomerById(savedCustomer.getCustomerId());


        assertNotNull(foundCustomer);
        assertEquals(savedCustomer.getCustomerId(), foundCustomer.getCustomerId());


    }
}
