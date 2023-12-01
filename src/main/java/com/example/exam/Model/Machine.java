package com.example.exam.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "machine_seq_gen")
    @SequenceGenerator(name = "machine_seg_gen", sequenceName = "machine_seq", allocationSize = 1)
    @Column(name = "machine_id")
    public Long machineId = 0L;


    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "subassembly_id")
    private Subassembly subassembly;
}