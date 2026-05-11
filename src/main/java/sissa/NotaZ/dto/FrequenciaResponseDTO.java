package sissa.NotaZ.dto;

import sissa.NotaZ.domain.Frequencia;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class FrequenciaResponseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private LocalDate data;
    private Boolean presente;
    private Long alunoId;
    private String alunoMatricula;
    private String alunoNome;
    private Long disciplinaId;
    private String disciplinaNome;

    public FrequenciaResponseDTO(){
    }

    public FrequenciaResponseDTO(Frequencia frequencia){
        id = frequencia.getId();
        data = frequencia.getData();
        presente = frequencia.getPresente();
        alunoId = frequencia.getAluno().getId();
        alunoMatricula = frequencia.getAluno().getMatricula();
        alunoNome = frequencia.getAluno().getUsuario().getNome();
        disciplinaId = frequencia.getDisciplina().getId();
        disciplinaNome = frequencia.getDisciplina().getNome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Boolean getPresente() {
        return presente;
    }

    public void setPresente(Boolean presente) {
        this.presente = presente;
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public String getAlunoMatricula() {
        return alunoMatricula;
    }

    public void setAlunoMatricula(String alunoMatricula) {
        this.alunoMatricula = alunoMatricula;
    }

    public String getAlunoNome() {
        return alunoNome;
    }

    public void setAlunoNome(String alunoNome) {
        this.alunoNome = alunoNome;
    }

    public Long getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(Long disciplinaId) {
        this.disciplinaId = disciplinaId;
    }

    public String getDisciplinaNome() {
        return disciplinaNome;
    }

    public void setDisciplinaNome(String disciplinaNome) {
        this.disciplinaNome = disciplinaNome;
    }
}
