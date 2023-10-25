package com.example.ssxyz_ltda.controllers;

import com.example.ssxyz_ltda.dtos.PlanilhaDTO;
import com.example.ssxyz_ltda.services.PlanilhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planilhas")
public class PlanilhaController {

    @Autowired
    private PlanilhaService planilhaService;

    @PostMapping
    public ResponseEntity<PlanilhaDTO> criar(@RequestBody PlanilhaDTO planilhaDTO) {
        PlanilhaDTO planilhaSalva = planilhaService.salvar(planilhaDTO);
        return new ResponseEntity<>(planilhaSalva, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PlanilhaDTO>> listar() {
        List<PlanilhaDTO> planilhas = planilhaService.obterTodasPlanilhas();
        return new ResponseEntity<>(planilhas, HttpStatus.OK);
    }

    @GetMapping("/professor/{professorId}")
    public ResponseEntity<List<PlanilhaDTO>> obterPorProfessor(@PathVariable Long professorId) {
        List<PlanilhaDTO> planilhasDoProfessor = planilhaService.obterPorProfessor(professorId);
        return new ResponseEntity<>(planilhasDoProfessor, HttpStatus.OK);
    }
    
}
