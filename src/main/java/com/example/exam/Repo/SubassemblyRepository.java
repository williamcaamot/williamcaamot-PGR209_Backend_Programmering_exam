package com.example.exam.Repo;

import com.example.exam.Model.Subassembly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubassemblyRepository extends JpaRepository<Subassembly, Long> {
}
