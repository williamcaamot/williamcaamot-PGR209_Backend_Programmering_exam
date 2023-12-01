package com.example.exam.Controller;

import com.example.exam.Model.Address;
import com.example.exam.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }


    @GetMapping("")
    public List<Address> getAddress(){
        List<Address> addressList = addressService.getAddress();
        System.out.println(addressList.get(0).getCustomers().get(0).getCustomerName());
        return addressService.getAddress();
    }

    @PostMapping("")
    public Address addAddress(@RequestBody Address address){
        return addressService.addAddress(address);
    }

}
