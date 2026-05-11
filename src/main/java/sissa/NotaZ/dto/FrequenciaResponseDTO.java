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
    private Integer quantidadeAulas;
    private Boolean presente;
    private Long alunoId;
    private String alunoMatricula;
    private String alunoNome;
    private Long aulaId;
    private Long disciplinaId;
    private String disciplinaNome;
    private Long turmaId;

    public FrequenciaResponseDTO(){
    }

    public FrequenciaResponseDTO(Frequencia frequencia){
        id = frequencia.getId();
        data = frequencia.getAula().getData();
        quantidadeAulas = frequencia.getAula().getQuantidadeAulas();
        presente = frequencia.getPresente();
        alunoId = frequencia.getAluno().getId();
        alunoMatricula = frequencia.getAluno().getMatricula();
        alunoNome = frequencia.getAluno().getUsuario().getNome();
        aulaId = frequencia.getAula().getId();
        disciplinaId = frequencia.getAula().getDisciplina().getId();
        disciplinaNome = frequencia.getAula().getDisciplina().getNome();
        turmaId = frequencia.getAula().getTurma().getId();
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

    public Integer getQuantidadeAulas() {
        return quantidadeAulas;
    }

    public void setQuantidadeAulas(Integer quantidadeAulas) {
        this.quantidadeAulas = quantidadeAulas;
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

    public Long getAulaId() {
        return aulaId;
    }

    public void setAulaId(Long aulaId) {
        this.aulaId = aulaId;
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

    public Long getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(Long turmaId) {
        this.turmaId = turmaId;
    }
}
