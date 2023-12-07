package com.example.exam.subassembly;

import com.example.exam.Model.Subassembly;
import com.example.exam.Repo.SubassemblyRepository;
import com.example.exam.Service.SubassemblyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SubassemblyUnitTest {

    @MockBean
    private SubassemblyRepository SubassemblyRepository;

    @Autowired
    private SubassemblyService SubassemblyService;

    @Test
    void shouldFetchSubassemblies(){
        List<Subassembly> SubassemblyList = List.of(new Subassembly(), new Subassembly());
        when(SubassemblyRepository.findAll()).thenReturn(SubassemblyList);

        var subassemblies = SubassemblyService.getSubassembly();
        assert subassemblies.size() == 2;
    }

    @Test
    public void getSubassemblyById(){
        Long exampleId = 1L;
        Subassembly mockSubassembly = new Subassembly();

        mockSubassembly.setSubassemblyId(exampleId);

        when(SubassemblyRepository.findById(exampleId)).thenReturn(Optional.of(mockSubassembly));

        Subassembly found = SubassemblyService.getSubassemblyById(exampleId);

        assertNotNull(found);
        assertEquals(exampleId, found.getSubassemblyId());

        verify(SubassemblyRepository).findById(exampleId);
    }
    
}
