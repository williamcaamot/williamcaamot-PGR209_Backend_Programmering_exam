package com.example.exam.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq_gen")
    @SequenceGenerator(name = "customer_seg_gen", sequenceName = "customer_seq", allocationSize = 1)
    @Column(name = "customer_id")
    private long customerId = 0L;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_email")
    private String customerEmail;

    @OneToMany(cascade = CascadeType.ALL)
    //@JsonIgnoreProperties("Address") add in later
    @JoinColumn(name = "address_id")
    private List<Address> addresses = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "order_id")
    private List<Order> orders;



    public Customer(String customerName, String customerEmail){
        this.customerName = customerName;
        this.customerEmail = customerEmail;
    }
}
