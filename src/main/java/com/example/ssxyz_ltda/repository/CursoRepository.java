package com.example.ssxyz_ltda.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ssxyz_ltda.models.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {
        List<Curso> findByNomeLike(String nome);
}
