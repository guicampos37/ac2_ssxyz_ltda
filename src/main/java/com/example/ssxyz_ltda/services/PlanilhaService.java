package com.example.ssxyz_ltda.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ssxyz_ltda.dtos.PlanilhaDTO;
import com.example.ssxyz_ltda.exceptions.RegraNegocioException;
import com.example.ssxyz_ltda.models.Curso;
import com.example.ssxyz_ltda.models.Planilha;
import com.example.ssxyz_ltda.models.Professor;
import com.example.ssxyz_ltda.repository.CursoRepository;
import com.example.ssxyz_ltda.repository.PlanilhaRepository;
import com.example.ssxyz_ltda.repository.ProfessorRepository;

@Service
public class PlanilhaService {

    @Autowired
    private PlanilhaRepository planilhaRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private CursoRepository cursoRepository;

    public List<PlanilhaDTO> obterTodasPlanilhas() {
        return planilhaRepository.findAll().stream().map(this::transformToDTO).collect(Collectors.toList());
    }

    public PlanilhaDTO obterPlanilhaPorId(Long id) {
        return planilhaRepository.findById(id).map(this::transformToDTO).orElse(null);
    }

    public PlanilhaDTO salvar(PlanilhaDTO dto) {
        Planilha planilha = transformToEntity(dto);
        Planilha planilhaSalva = planilhaRepository.save(planilha);
        return transformToDTO(planilhaSalva);
    }

    private PlanilhaDTO transformToDTO(Planilha planilha) {
        return PlanilhaDTO.builder()
                .id(planilha.getId())
                .dataInicio(planilha.getDataInicio())
                .dataFim(planilha.getDataFim())
                .horarioInicio(planilha.getHorarioInicio())
                .horarioFim(planilha.getHorarioFim())
                .nomeProfessor(planilha.getProfessor().getNome())
                .cidade(planilha.getCidade())
                .uf(planilha.getUf())
                .cep(planilha.getCep())
                .nomeCurso(planilha.getCurso().getNome())
                .cursoId(planilha.getCurso().getId())
                .professorId(planilha.getProfessor().getId())
                .build();
    }
    
    private Planilha transformToEntity(PlanilhaDTO dto) {        
        Professor professor = professorRepository.findById(dto.getProfessorId())
                                 .orElseThrow(() -> new RegraNegocioException("Professor não encontrado!"));
        Curso curso = cursoRepository.findById(dto.getCursoId())
                          .orElseThrow(() -> new RegraNegocioException("Curso não encontrado!"));
        
        Planilha planilha = new Planilha();
        planilha.setDataInicio(dto.getDataInicio());
        planilha.setDataFim(dto.getDataFim());
        planilha.setHorarioInicio(dto.getHorarioInicio());
        planilha.setHorarioFim(dto.getHorarioFim());
        planilha.setProfessor(professor);
        planilha.setCidade(dto.getCidade());
        planilha.setUf(dto.getUf());
        planilha.setCep(dto.getCep());
        planilha.setCurso(curso);
    
        return planilha;
    }
    
    public List<PlanilhaDTO> obterPorProfessor(Long professorId) {
        List<Planilha> planilhas = planilhaRepository.findByProfessorId(professorId);
        return planilhas.stream().map(this::transformToDTO).collect(Collectors.toList());
    }
    
}
