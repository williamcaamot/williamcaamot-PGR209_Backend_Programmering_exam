package com.example.exam.part;

import com.example.exam.Model.Part;
import com.example.exam.Repo.PartRepository;
import com.example.exam.Service.PartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class PartServiceUnitTest {

    @MockBean
    private PartRepository partRepository;

    @Autowired
    private PartService partService;

    @Test
    void shouldFetchParts(){
        List<Part> partList = List.of(new Part(), new Part());
        when(partRepository.findAll()).thenReturn(partList);

        var parts = partService.getParts();
        assert parts.size() == 2;
    }
}
