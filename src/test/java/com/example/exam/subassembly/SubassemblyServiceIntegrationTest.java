package com.example.exam.subassembly;

import com.example.exam.Model.Subassembly;
import com.example.exam.Repo.SubassemblyRepository;
import com.example.exam.Service.SubassemblyService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SubassemblyServiceIntegrationTest {

    @Autowired
    private SubassemblyService SubassemblyService;

    @Autowired
    private SubassemblyRepository SubassemblyRepository;

    @Test
    @Transactional
    void shouldGetPaginatedSubassemblies() {

        Subassembly aTestSubassembly = new Subassembly();
        SubassemblyRepository.save(aTestSubassembly);

        Page<Subassembly> subassemblyPage = SubassemblyService.getPaginatedSubassembly(0, 10);
        List<Subassembly> subassemblies = subassemblyPage.getContent();

        assertNotNull(subassemblies);
        assertFalse(subassemblies.isEmpty());
        //dummy line
    }

    @Test
    @Transactional
    void shouldFetchSubassemblies(){

        Subassembly aNewSubassembly = new Subassembly();
        Subassembly savedSubassembly = SubassemblyService.addSubassembly(aNewSubassembly);

        Subassembly foundSubassembly = SubassemblyService.getSubassemblyById(savedSubassembly.getSubassemblyId());


        assertNotNull(foundSubassembly);
        assertEquals(savedSubassembly.getSubassemblyId(), foundSubassembly.getSubassemblyId());


    }
}
