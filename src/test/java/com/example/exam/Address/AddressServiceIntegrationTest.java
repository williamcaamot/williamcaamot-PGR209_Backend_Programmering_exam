package com.example.exam.Address;

import com.example.exam.Service.AddressService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class AddressServiceIntegrationTest {

    @Autowired
    private AddressService addressService;

    @Test
    @Transactional
    void shouldFetchAddresses(){

        var addresses = addressService.getAddress();

        /*assert addresses.size() == 1;
        assert addresses.get(0).getCustomers() */


    }

}
