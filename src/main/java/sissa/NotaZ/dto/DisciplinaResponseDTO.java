package sissa.NotaZ.dto;

import sissa.NotaZ.domain.Disciplina;

import java.io.Serial;
import java.io.Serializable;

public class DisciplinaResponseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private Long professorId;
    private String professorRa;
    private String professorNome;

    public DisciplinaResponseDTO(){
    }

    public DisciplinaResponseDTO(Disciplina disciplina){
        id = disciplina.getId();
        nome = disciplina.getNome();
        professorId = disciplina.getProfessor().getId();
        professorRa = disciplina.getProfessor().getRa();
        professorNome = disciplina.getProfessor().getUsuario().getNome();
    }

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

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public String getProfessorRa() {
        return professorRa;
    }

    public void setProfessorRa(String professorRa) {
        this.professorRa = professorRa;
    }

    public String getProfessorNome() {
        return professorNome;
    }

    public void setProfessorNome(String professorNome) {
        this.professorNome = professorNome;
    }
}
