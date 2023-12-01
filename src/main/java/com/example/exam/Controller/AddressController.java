package com.example.exam.Controller;

import com.example.exam.Model.Address;
import com.example.exam.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    private AddressService addressService;

    //Test with pagination
    public ResponseEntity<List<Address>> getAddresses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        Page<Address> pageResult = addressService.getPaginatedAddresses(page, size);

        if (!pageResult.isEmpty()){
            return new ResponseEntity<>(pageResult.getContent(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }


    @GetMapping("")
    public List<Address> getAddress() {
        return addressService.getAddress();
    }

    @GetMapping("/{id}")
    public Address getAddress(@PathVariable Long id) {
        return addressService.getAddressById(id);
    }

    @PostMapping("")
    public Address addAddress(@RequestBody Address address) {
        return addressService.addAddress(address);
    }

    @DeleteMapping("")
    public void deleteAddress(@RequestBody Address address) {
        addressService.deleteAddress(address);
        return;
    }

    @PutMapping("")
    public Address updateAddress(@RequestBody Address address) {
        return addressService.updateAddress(address);

    }
}