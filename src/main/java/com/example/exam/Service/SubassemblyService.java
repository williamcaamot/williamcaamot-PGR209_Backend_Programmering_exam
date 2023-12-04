package com.example.exam.Service;

import com.example.exam.Model.Part;
import com.example.exam.Model.Subassembly;
import com.example.exam.Repo.SubassemblyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubassemblyService {

    SubassemblyRepository subassemblyRepository;

    @Autowired
    public SubassemblyService(SubassemblyRepository subassemblyRepository) {
        this.subassemblyRepository = subassemblyRepository;
    }

    public Page<Subassembly> getPaginatedSubassembly(int pageNumber, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return subassemblyRepository.findAll(pageRequest);
    }

    public Subassembly getSubassemblyById(Long id){
        return subassemblyRepository.findById(id).orElse(null);
    }
    public List<Subassembly> getSubassembly(){
        return subassemblyRepository.findAll();
    }

    public Subassembly addSubassembly(Subassembly subassembly){
        return subassemblyRepository.save(subassembly);
    }

    public void deleteSubassembly(Subassembly subassembly){
        subassemblyRepository.delete(subassembly);
    }
    public Subassembly updateSubassembly(Subassembly subassembly){
        return subassemblyRepository.save(subassembly);
    }
}
