package com.example.ssxyz_ltda.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.ssxyz_ltda.dtos.CategoriaCursoDTO;
import com.example.ssxyz_ltda.dtos.CursoDTO;
import com.example.ssxyz_ltda.dtos.DadosCursoDTO;
import com.example.ssxyz_ltda.dtos.ProfessorDTO;
import com.example.ssxyz_ltda.exceptions.RegraNegocioException;
import com.example.ssxyz_ltda.models.CategoriaCurso;
import com.example.ssxyz_ltda.models.Curso;
import com.example.ssxyz_ltda.models.Professor;
import com.example.ssxyz_ltda.repository.CategoriaCursoRepository;
import com.example.ssxyz_ltda.repository.CursoRepository;
import com.example.ssxyz_ltda.repository.ProfessorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;
    private final CategoriaCursoRepository categoriaCursoRepository;
    private final ProfessorRepository professorRepository;

    @Override
    public Curso salvar(CursoDTO cursoDTO) {
    CategoriaCurso categ = categoriaCursoRepository.findById(cursoDTO.getCategoriaCursoId()).orElseThrow(
            () -> new RegraNegocioException("Código da categoria não encontrado!")
    );

        Curso c = new Curso();
        c.setCargaHoraria(cursoDTO.getCargaHoraria());
        c.setCategoriaCurso(categ);
        c.setNome(cursoDTO.getNome());

        if (cursoDTO.getProfessorIds() != null && !cursoDTO.getProfessorIds().isEmpty()) {
            List<Professor> professores = professorRepository.findAllById(cursoDTO.getProfessorIds());
            
            if (professores.size() != cursoDTO.getProfessorIds().size()) {
                throw new RegraNegocioException("Alguns IDs de professores não foram encontrados!");
            }
            
            c.setProfessoresHabilitados(new ArrayList<>(professores));
        }

        // Salvar o curso no banco de dados
        return cursoRepository.save(c);
    }


    public List<CursoDTO> listarTodos() {
        List<CursoDTO> cursos = cursoRepository.findAll().stream().map(
            (Curso c) -> {
                return CursoDTO.builder()
                .id(c.getId())
                .nome(c.getNome())
                .cargaHoraria(c.getCargaHoraria())
                .categoriaCursoId(c.getCategoriaCurso() == null ? 0 : c.getCategoriaCurso().getId())
                .professorIds(c.getProfessoresHabilitados().stream().map(Professor::getId).collect(Collectors.toList())).build();
            }
        ).collect(Collectors.toList());
        return cursos;
    }

    public DadosCursoDTO obterPorId(Long id) {
        return cursoRepository.findById(id).map((Curso c) -> {
            List<ProfessorDTO> professorDTOs = c.getProfessoresHabilitados().stream()
                .map(professor -> ProfessorDTO.builder()
                    .id(professor.getId())
                    .nome(professor.getNome())
                    .celular(professor.getCelular())
                    .cpf(professor.getCpf())
                    .rg(professor.getRg())
                    .endereco(professor.getEndereco())
                    .build())
                .collect(Collectors.toList());
    
            return DadosCursoDTO.builder()
                .id(c.getId())
                .nome(c.getNome())
                .cargaHoraria(c.getCargaHoraria())
                .categoria(c.getCategoriaCurso() != null ? 
                    CategoriaCursoDTO.builder()
                        .id(c.getCategoriaCurso().getId())
                        .nome(c.getCategoriaCurso().getNome())
                    .build() : null
                )
                .professores(professorDTOs)
                .build();
        }).orElseThrow(() -> new RegraNegocioException("Id do curso não encontrado"));
    }
    

    public void excluir(Long id) {
        cursoRepository.deleteById(id);
    }

    @Override
    public void editar(Long id, CursoDTO dto) {
        Curso curso = cursoRepository.findById(id)
        .orElseThrow(() -> new RegraNegocioException("Código usuário não encontrado."));
        CategoriaCurso categoriaCurso =
        categoriaCursoRepository.findById(dto.getCategoriaCursoId())
        .orElseThrow(() -> new RegraNegocioException("Categoria não encontrado."));
        curso.setNome(dto.getNome());
        curso.setCargaHoraria(dto.getCargaHoraria());
        curso.setCategoriaCurso(categoriaCurso);
        if (dto.getProfessorIds() != null && !dto.getProfessorIds().isEmpty()) {
            List<Professor> professores = professorRepository.findAllById(dto.getProfessorIds());
            
            if (professores.size() != dto.getProfessorIds().size()) {
                throw new RegraNegocioException("Alguns IDs de professores não foram encontrados!");
            }

            curso.setProfessoresHabilitados(professores);
        } else {
            curso.setProfessoresHabilitados(new ArrayList<>()); // Se não houver IDs de professores, limpe a lista
        }
        cursoRepository.save(curso);
    }

}
