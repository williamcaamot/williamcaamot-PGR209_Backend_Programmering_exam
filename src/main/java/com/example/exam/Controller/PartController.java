package com.example.exam.Controller;

import com.example.exam.Model.CustomerOrder;
import com.example.exam.Model.Part;
import com.example.exam.Service.PartService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/part")
public class PartController {

    PartService partService;

    @Autowired
    public PartController(PartService partService) {
        this.partService = partService;
    }

    /*@GetMapping
    public List<Part> getParts(){
        return partService.getParts();
    } */

    @GetMapping("")
    public ResponseEntity<List<Part>> getParts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Part> pageResult = partService.getPaginatedParts(page, size);

        if (!pageResult.isEmpty()) {
            return new ResponseEntity<>(pageResult.getContent(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public Part getPartById(@PathVariable Long id) {
        return partService.getPartById(id);
    }

    @PostMapping("")
    public ResponseEntity<Part> addPart(@RequestBody Part part) {
        Part addedPart = partService.addPart(part);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedPart);
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deletePart(@RequestBody Part part) {
        partService.deletePart(part);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("")
    public ResponseEntity<Part> updatePart(@RequestBody Part part) {
        Part updatedPart = partService.updatePart(part);
        return ResponseEntity.status(HttpStatus.OK).body(updatedPart);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
