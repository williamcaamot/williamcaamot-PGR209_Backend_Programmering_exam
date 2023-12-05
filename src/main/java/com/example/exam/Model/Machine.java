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
    private Long machineId = 0L;

    @Column(name="machine_name")
    private String machineName;

    @Column(name="machine_description")
    private String machineDescription;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="machine_subassembly",
            joinColumns = @JoinColumn(name="machine_id"),
            inverseJoinColumns = @JoinColumn(name="subassembly_id")
    )
    private List<Subassembly> subassemblies;
}