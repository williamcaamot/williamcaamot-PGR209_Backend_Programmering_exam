package com.example.exam.Address;

import com.example.exam.Model.Address;
import com.example.exam.Repo.AddressRepository;
import com.example.exam.Service.AddressService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;


@SpringBootTest
public class AddressServiceIntegrationTest {

    @Autowired
    private AddressService addressService;

    @Autowired
    private AddressRepository addressRepository;

    @Test
    @Transactional
    void shouldGetPaginatedAddresses() {

        Address aTestAddress = new Address();
        addressRepository.save(aTestAddress);

        Page<Address> addressesPage = addressService.getPaginatedAddresses(0, 10);
        List<Address> addresses = addressesPage.getContent();

        assertNotNull(addresses);
        assertFalse(addresses.isEmpty());
    }

        @Test
        @Transactional
        void shouldFetchAddresses(){

            Address aNewAddress = new Address();
            Address savedAddress = addressService.addAddress(aNewAddress);

            Address foundAddress = addressService.getAddressById(savedAddress.getAddressId());


            assertNotNull(foundAddress);
            assertEquals(savedAddress.getAddressId(), foundAddress.getAddressId());


        }


    }


