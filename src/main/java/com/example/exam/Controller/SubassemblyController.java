package com.example.exam.Controller;

import com.example.exam.Model.Subassembly;
import com.example.exam.Service.SubassemblyService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public List<Subassembly> getSubassembly(){
        return subassemblyService.getSubassembly();
    }

    @GetMapping("/{id}")
    public Subassembly getSubassemblyById(@PathVariable Long id){
        return subassemblyService.getSubassemblyById(id);
    }

    @PostMapping("")
    public Subassembly addSubassembly(@RequestBody Subassembly subassembly){
        return subassemblyService.addSubassembly(subassembly);
    }

    @DeleteMapping("")
    public void deleteSubassembly(@RequestBody Subassembly subassembly){
        subassemblyService.deleteSubassembly(subassembly);
    }

    @PutMapping("")
    public Subassembly updateSubassembly(@RequestBody Subassembly subassembly){
        return subassemblyService.updateSubassembly(subassembly);
    }

}
