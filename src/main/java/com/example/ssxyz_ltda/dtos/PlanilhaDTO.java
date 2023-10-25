package com.example.ssxyz_ltda.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlanilhaDTO {
    private Long id;
    private String dataInicio;
    private String dataFim;
    private String horarioInicio;
    private String horarioFim;
    private Long professorId;
    private Long cursoId;
    private String cidade;
    private String uf;
    private String cep;
    private String nomeProfessor; 
    private String nomeCurso;
}
