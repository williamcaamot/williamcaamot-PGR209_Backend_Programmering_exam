package com.example.exam.Controller;

import com.example.exam.Model.Part;
import com.example.exam.Service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public List<Part> getParts(){
        return partService.getParts();
    }

    @GetMapping("/{id}")
    public Part getPartById(@PathVariable Long id){
        return partService.getPartById(id);
    }

    @PostMapping("")
    public ResponseEntity<Part> addPart(@RequestBody Part part){
        Part addedPart = partService.addPart(part);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedPart);
    }

    @DeleteMapping("")
    public void deletePart(@RequestBody Part part){
        partService.deletePart(part);
    }

    @PutMapping("")
    public Part updatePart(@RequestBody Part part){
        return partService.updatePart(part);
    }

}
