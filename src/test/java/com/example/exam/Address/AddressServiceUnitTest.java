package com.example.exam.Address;

import com.example.exam.Model.Address;
import com.example.exam.Repo.AddressRepository;
import com.example.exam.Service.AddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AddressServiceUnitTest {

    @MockBean
    private AddressRepository addressRepository;

    @Autowired
    private AddressService addressService;

    @Test
    void shouldFetchAddresses(){
        List<Address> addressList = List.of(new Address(), new Address(), new Address());
        when(addressRepository.findAll()).thenReturn(addressList);

        var addresses = addressService.getAddress();
        assert addresses.size() == 3;
    }

    @Test
    public void getAddressById(){
        Long exampleId = 1L;
        Address mockAddress = new Address();

        mockAddress.setAddressId(exampleId);

        when(addressRepository.findById(exampleId)).thenReturn(Optional.of(mockAddress));

        Address found = addressService.getAddressById(exampleId);

        assertNotNull(found);
        assertEquals(exampleId, found.getAddressId());

        verify(addressRepository).findById(exampleId);
    }

}
