package com.example.exam.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq_gen")
    @SequenceGenerator(name = "address_seg_gen", sequenceName = "address_seq", allocationSize = 1)
    @Column(name = "address_id")
    public Long addressId = 0L;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_address")
    private String customerAddress;

    @OneToMany(cascade = CascadeType.ALL)
    //@JsonIgnoreProperties("customer") comes in later
    @JoinColumn(name = "customer_id")
    private List<Customer> customers = new ArrayList<>();

    public Address(String customerName, String customerAddress){
        this.customerName = customerName;
        this.customerAddress = customerAddress;
    }



}