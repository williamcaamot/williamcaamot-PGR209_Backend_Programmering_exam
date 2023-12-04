package com.example.exam.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "part_seq_gen")
    @SequenceGenerator(name = "part_seg_gen", sequenceName = "part_seq", allocationSize = 1)
    @Column(name = "part_id")
    private Long partId = 0L;

    @Column(name="part_name")
    private String partName;

    @Column(name="part_description")
    private String partDescription;
}
