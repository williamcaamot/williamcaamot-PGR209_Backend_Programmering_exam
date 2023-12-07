package com.example.exam.Address;

import com.example.exam.Model.Address;
import com.example.exam.Repo.AddressRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
public class AddressRepoTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void deleteAddressTest(){
        Address testAddress = new Address();
        testAddress = testEntityManager.persistFlushFind(testAddress);

        addressRepository.delete(testAddress);

        Optional<Address> deleteAddress = addressRepository.findById(testAddress.getAddressId());
        assertFalse(deleteAddress.isPresent());
    }

    @Test
    public void addAddressTest(){
        Address newAddress = new Address();

        Address savedAddress = addressRepository.save(newAddress);

        assertNotNull(savedAddress.getAddressId());
        Address foundAddress = testEntityManager.find(Address.class, savedAddress.getAddressId());
        assertNotNull(foundAddress);


    }
}
