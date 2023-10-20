package com.example.ssxyz_ltda.services;

import java.util.List;

import com.example.ssxyz_ltda.dtos.CursoDTO;
import com.example.ssxyz_ltda.dtos.DadosCursoDTO;
import com.example.ssxyz_ltda.models.Curso;

public interface CursoService {
    Curso salvar(CursoDTO cursoDTO);
    List<CursoDTO> listarTodos();
    DadosCursoDTO obterPorId(Long id);
    void excluir(Long id);
    void editar(Long id, CursoDTO dto);
}
