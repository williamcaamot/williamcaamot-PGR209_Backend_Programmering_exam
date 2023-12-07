package com.example.exam.Customer;

import com.example.exam.Model.Customer;
import com.example.exam.Repo.CustomerRepository;
import com.example.exam.Service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerServiceUnitTest {

    @MockBean
    private CustomerRepository CustomerRepository;

    @Autowired
    private CustomerService CustomerService;

    @Test
    void shouldFetchCustomeres(){
        List<Customer> CustomerList = List.of(new Customer(), new Customer());
        when(CustomerRepository.findAll()).thenReturn(CustomerList);

        var customers = CustomerService.getCustomers();
        assert customers.size() == 2;
    }
}
