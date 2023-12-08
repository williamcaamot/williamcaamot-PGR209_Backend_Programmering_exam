package com.example.exam.Customer;

import com.example.exam.Model.Address;
import com.example.exam.Model.Customer;
import com.example.exam.Repo.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Optional;

@DataJpaTest
public class CustomerRepoTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void deleteCustomerTest(){
        Customer testCustomer = new Customer();
        testCustomer = testEntityManager.persistFlushFind(testCustomer);

        customerRepository.delete(testCustomer);

        Optional<Customer> deleteCustomer = customerRepository.findById(testCustomer.getCustomerId());
        assertFalse(deleteCustomer.isPresent());
    }

    @Test
    public void shouldAddAddressToCustomerTest(){
        Customer testCustomr = new Customer();
        testCustomr = testEntityManager.persistAndFlush(testCustomr);

        Address testAddress = new Address();
        testAddress = testEntityManager.persistAndFlush(testAddress);

        testCustomr.getAddresses().add(testAddress);
        testAddress.getCustomers().add(testCustomr);


        testCustomr = testEntityManager.persistAndFlush(testCustomr);


        Optional<Customer> resultCustomer = customerRepository.findById(testCustomr.getCustomerId());

        assertTrue(resultCustomer.isPresent());
        assertTrue(resultCustomer.get().getAddresses().contains(testAddress));
    }
}
