package com.example.exam.Controller;

import com.example.exam.Model.Part;
import com.example.exam.Model.Subassembly;
import com.example.exam.Service.SubassemblyService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subassembly")
public class SubassemblyController {

    SubassemblyService subassemblyService;

    @Autowired
    public SubassemblyController(SubassemblyService subassemblyService) {
        this.subassemblyService = subassemblyService;
    }

    @GetMapping("/{id}")
    public Subassembly getSubassemblyById(@PathVariable Long id){
        return subassemblyService.getSubassemblyById(id);
    }

    @GetMapping("")
    public ResponseEntity<List<Subassembly>> getSubassembly(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        Page<Subassembly> pageResult = subassemblyService.getPaginatedSubassembly(page, size);

        if (!pageResult.isEmpty()){
            return new ResponseEntity<>(pageResult.getContent(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("")
    public ResponseEntity<Subassembly> addSubassembly(@RequestBody Subassembly subassembly){
        Subassembly addedSubassembly = subassemblyService.addSubassembly(subassembly);
        return ResponseEntity.status(HttpStatus.CREATED).body(subassembly);
    }
    @DeleteMapping("")
    public void deleteSubassembly(@RequestBody Subassembly subassembly){
        subassemblyService.deleteSubassembly(subassembly);
    }

    @PutMapping("")
    public Subassembly updateSubassembly(@RequestBody Subassembly subassembly){
        return subassemblyService.updateSubassembly(subassembly);
    }




    @PostMapping("/{subassemblyId}/part/{partId}")
    public ResponseEntity<Subassembly> addPartToSubassembly(@PathVariable Long subassemblyId,@PathVariable Long partId){
        Subassembly subassembly = subassemblyService.addPartToSubassembly(subassemblyId, partId);
        return ResponseEntity.status(HttpStatus.CREATED).body(subassembly);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
