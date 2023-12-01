package com.example.exam.Service;

import com.example.exam.Model.Part;
import com.example.exam.Repo.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartService {
    
    PartRepository partRepository;

    @Autowired
    public PartService(PartRepository partRepository) {
        this.partRepository = partRepository;
    }


    public Part getPartById(Long id){
        return partRepository.findById(id).orElse(null);
    }
    public List<Part> getPart(){
        return partRepository.findAll();
    }

    public Part addPart(Part part){
        return partRepository.save(part);
    }

    public void deletePart(Part part){
        partRepository.delete(part);
    }
    public Part updatePart(Part part){
        return partRepository.save(part);
    }
    
    
}
