package com.example.ssxyz_ltda.dtos;

import org.hibernate.mapping.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CursoDTO {
    private Long id;
    private String nome;
    private int cargaHoraria;
    private Integer categoriaCursoId;
    private java.util.List<Long> professorIds;
}
