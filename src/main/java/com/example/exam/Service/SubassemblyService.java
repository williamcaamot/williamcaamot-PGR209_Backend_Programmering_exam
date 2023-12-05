package com.example.exam.Service;

import com.example.exam.Model.Part;
import com.example.exam.Model.Subassembly;
import com.example.exam.Repo.PartRepository;
import com.example.exam.Repo.SubassemblyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubassemblyService {

    SubassemblyRepository subassemblyRepository;
    PartRepository partRepository;

    @Autowired
    public SubassemblyService(SubassemblyRepository subassemblyRepository, PartRepository partRepository) {
        this.subassemblyRepository = subassemblyRepository;
        this.partRepository = partRepository;
    }

    public Subassembly getSubassemblyById(Long id){
        return subassemblyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Subassembly with ID " + id + " could not be found!"));
    }

    public Page<Subassembly> getPaginatedSubassembly(int pageNumber, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return subassemblyRepository.findAll(pageRequest);
    }

    public List<Subassembly> getSubassembly(){
        return subassemblyRepository.findAll();
    }

    public Subassembly addSubassembly(Subassembly subassembly){
        return subassemblyRepository.save(subassembly);
    }

    public void deleteSubassembly(Subassembly subassembly){
        subassemblyRepository.findById(subassembly.getSubassemblyId()).orElseThrow(() -> new EntityNotFoundException("Subassembly with ID " + subassembly.getSubassemblyId() + " could not be found!"));
        subassemblyRepository.delete(subassembly);
    }
    public Subassembly updateSubassembly(Subassembly subassembly){
        subassemblyRepository.findById(subassembly.getSubassemblyId()).orElseThrow(() -> new EntityNotFoundException("Subassembly with ID " + subassembly.getSubassemblyId() + " could not be found!"));
        return subassemblyRepository.save(subassembly);
    }



    public Subassembly addPartToSubassembly(Long subassemblyId, Long partId){
        Subassembly subassembly = subassemblyRepository.findById(subassemblyId).orElseThrow(() -> new EntityNotFoundException("Subassembly with ID " + subassemblyId + " could not be found!"));
        Part part = partRepository.findById(partId).orElseThrow(() -> new EntityNotFoundException("Part with ID " + partId + " could not be found!"));
        subassembly.getParts().add(part);
        return subassemblyRepository.save(subassembly);
    }


}
