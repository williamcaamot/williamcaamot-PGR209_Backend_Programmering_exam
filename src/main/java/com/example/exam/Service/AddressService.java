package com.example.exam.Service;

import com.example.exam.Controller.AddressController;
import com.example.exam.Model.Address;
import com.example.exam.Repo.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    public Address getOneById(Long id){
        return new Address();
    }


}
