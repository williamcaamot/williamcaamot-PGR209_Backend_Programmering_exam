package com.example.exam.Controller;

import com.example.exam.Model.Address;
import com.example.exam.Service.AddressService;
import jakarta.persistence.EntityNotFoundException;
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




    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }


    //@GetMapping("")
    //public List<Address> getAddress() {
    //    return addressService.getAddress();
    //}

    //Test with pagination
    @GetMapping("")
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

    @GetMapping("/{id}")
    public Address getAddress(@PathVariable Long id) {
        return addressService.getAddressById(id);
    }

    @PostMapping("")
    public ResponseEntity<Address> addAddress(@RequestBody Address address) {
        Address addedAddress = addressService.addAddress(address);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedAddress);
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

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}