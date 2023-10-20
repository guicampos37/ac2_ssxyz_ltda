package com.example.ssxyz_ltda.models;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 200, nullable = false)
    private String nome;
    @Column(nullable = false, unique = true)
    private String cpf;
    @Column(nullable = false)
    private String rg;
    @Column(length = 200)
    private String endereco;
    @Column(nullable = false)
    private String celular;

    @ManyToMany(mappedBy = "professoresHabilitados")
    private Set<Curso> cursosHabilitados;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Set<Curso> getCursosHabilitados() {
        return cursosHabilitados;
    }

    public void setCursosHabilitados(Set<Curso> cursosHabilitados) {
        this.cursosHabilitados = cursosHabilitados;
    }

}

