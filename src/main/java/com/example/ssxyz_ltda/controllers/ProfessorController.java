package com.example.ssxyz_ltda.controllers;

import com.example.ssxyz_ltda.models.Professor;
import com.example.ssxyz_ltda.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public List<Professor> listarTodos() {
        return professorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> buscarPorId(@PathVariable Long id) {
        return professorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Professor criar(@RequestBody Professor professor) {
        return professorService.save(professor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> atualizar(@PathVariable Long id, @RequestBody Professor professor) {
        if (!professorService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        professor.setId(id);
        professorService.save(professor);
        return ResponseEntity.ok(professor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (!professorService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        professorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
