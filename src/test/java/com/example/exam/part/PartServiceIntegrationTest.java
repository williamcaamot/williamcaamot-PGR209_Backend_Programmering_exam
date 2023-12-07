package com.example.exam.part;

import com.example.exam.Model.Part;
import com.example.exam.Repo.PartRepository;
import com.example.exam.Service.PartService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PartServiceIntegrationTest {

    @Autowired
    private PartService PartService;

    @Autowired
    private PartRepository PartRepository;

    @Test
    @Transactional
    void shouldGetPaginatedParts() {

        Part aTestPart = new Part();
        PartRepository.save(aTestPart);

        Page<Part> partsPage = PartService.getPaginatedParts(0, 10);
        List<Part> parts = partsPage.getContent();

        assertNotNull(parts);
        assertFalse(parts.isEmpty());
    }

    @Test
    @Transactional
    void shouldFetchParts(){

        Part aNewPart = new Part();
        Part savedPart = PartService.addPart(aNewPart);

        Part foundPart = PartService.getPartById(savedPart.getPartId());


        assertNotNull(foundPart);
        assertEquals(savedPart.getPartId(), foundPart.getPartId());


    }
}
