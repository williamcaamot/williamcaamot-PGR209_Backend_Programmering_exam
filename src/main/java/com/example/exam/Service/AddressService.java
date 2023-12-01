package com.example.exam.Service;

import com.example.exam.Model.Address;
import com.example.exam.Repo.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    AddressRepository addressRepository;

    //Test with pagination, applies to repo, service and controller
    public Page<Address> getPaginatedAddresses(int pageNumber, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return addressRepository.findAll(pageRequest);
    }

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    public Address getAddressById(Long id){
        return addressRepository.findById(id).orElse(null);
    }
    public List<Address> getAddress(){
        return addressRepository.findAll();
    }

    public Address addAddress(Address address){
        return addressRepository.save(address);
    }

    public void deleteAddress(Address address){
        addressRepository.delete(address);
    }
    public Address updateAddress(Address address){
        return addressRepository.save(address);
    }


}
