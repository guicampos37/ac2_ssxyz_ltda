package com.example.ssxyz_ltda.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfessorDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String rg;
    private String endereco;
    private String celular;
}
