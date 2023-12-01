package com.example.exam.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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


}