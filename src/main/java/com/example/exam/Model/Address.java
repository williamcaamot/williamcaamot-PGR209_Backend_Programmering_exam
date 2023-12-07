package com.example.exam.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Long addressId = 0L;


    @Column(name = "address_country")
    private String country;

    @Column(name="address_area")
    private String area;

    @Column(name = "address_street_and_number")
    private String streetAndNumber;

    @JsonIgnore
    @ManyToMany(mappedBy = "addresses", cascade = CascadeType.ALL)
    private List<Customer> customers = new ArrayList<>();

    public Address(String country, String area, String streetAndNumber) {
        this.country = country;
        this.area = area;
        this.streetAndNumber = streetAndNumber;
    }
}