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
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "machine_seq_gen")
    @SequenceGenerator(name = "machine_seg_gen", sequenceName = "machine_seq", allocationSize = 1)
    @Column(name = "machine_id")
    public Long machineId = 0L;

    @Column(name="machine_name")
    private String machineName;

    @Column(name="machine_description")
    private String machineDescription;


    @ManyToOne
    @JoinColumn(name = "order_id")
    private CustomerOrder customerOrder;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "subassembly_id")
    private List<Subassembly> subassemblies;
}