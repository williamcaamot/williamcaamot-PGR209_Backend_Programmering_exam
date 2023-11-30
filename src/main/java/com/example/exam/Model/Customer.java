package com.example.exam.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    //TODO; add relations


    public Customer(String customerName, String customerEmail){
        this.customerName = customerName;
        this.customerEmail = customerEmail;
    }
}
