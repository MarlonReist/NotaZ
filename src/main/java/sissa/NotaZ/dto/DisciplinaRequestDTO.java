package sissa.NotaZ.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class DisciplinaRequestDTO implements Serializable {
    @NotBlank(message = "Nome é obrigatório!")
    private String nome;
    @NotNull(message = "Professor é obrigatório!")
    private Long professorId;

    public DisciplinaRequestDTO(){}

    public DisciplinaRequestDTO(String nome, Long professorId) {
        this.nome = nome;
        this.professorId = professorId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }
}

