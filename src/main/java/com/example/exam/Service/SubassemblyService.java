package com.example.exam.Service;

import com.example.exam.Model.Subassembly;
import com.example.exam.Repo.SubassemblyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubassemblyService {

    SubassemblyRepository subassemblyRepository;

    @Autowired
    public SubassemblyService(SubassemblyRepository subassemblyRepository) {
        this.subassemblyRepository = subassemblyRepository;
    }



    public Subassembly getAddressById(Long id){
        return subassemblyRepository.findById(id).orElse(null);
    }
    public List<Subassembly> getAddress(){
        return subassemblyRepository.findAll();
    }

    public Subassembly addAddress(Subassembly subassembly){
        return subassemblyRepository.save(subassembly);
    }

    public void deleteAddress(Subassembly subassembly){
        subassemblyRepository.delete(subassembly);
    }
    public Subassembly updateAddress(Subassembly subassembly){
        return subassemblyRepository.save(subassembly);
    }
}
