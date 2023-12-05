package com.example.exam.Service;

import com.example.exam.Model.CustomerOrder;
import com.example.exam.Model.Part;
import com.example.exam.Repo.PartRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartService {
    
    PartRepository partRepository;

    @Autowired
    public PartService(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    public Page<Part> getPaginatedParts(int pageNumber, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return partRepository.findAll(pageRequest);
    }

    public List<Part> getParts(){
        return partRepository.findAll();
    }


    public Part getPartById(Long id){
        return partRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Part with ID " + id + " could not be found!"));
    }

    public Part addPart(Part part){
        return partRepository.save(part);
    }

    public void deletePart(Part part){
        partRepository.findById(part.getPartId()).orElseThrow(() -> new EntityNotFoundException("Part with ID " + part.getPartId() + " could not be found!"));
        partRepository.delete(part);
    }
    public Part updatePart(Part part){
        partRepository.findById(part.getPartId()).orElseThrow(() -> new EntityNotFoundException("Part with ID " + part.getPartId() + " could not be found!"));
        return partRepository.save(part);
    }
    
    
}
