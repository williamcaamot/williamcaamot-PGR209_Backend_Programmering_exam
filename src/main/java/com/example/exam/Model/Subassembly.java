package com.example.exam.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Subassembly {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subassembly_seq_gen")
    @SequenceGenerator(name = "subassembly_seg_gen", sequenceName = "subassembly_seq", allocationSize = 1)
    @Column(name = "subassembly_id")
    private Long subassemblyId = 0L;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "part_id")
    private List<Part> parts;

}
