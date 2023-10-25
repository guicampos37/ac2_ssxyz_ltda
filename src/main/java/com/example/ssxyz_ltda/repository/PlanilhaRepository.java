package com.example.ssxyz_ltda.repository;

import com.example.ssxyz_ltda.models.Planilha;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlanilhaRepository extends JpaRepository<Planilha, Long> {
    
    List<Planilha> findByProfessorId(Long professorId);
}
