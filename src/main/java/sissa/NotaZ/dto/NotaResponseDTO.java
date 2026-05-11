package sissa.NotaZ.dto;

import sissa.NotaZ.domain.Nota;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class NotaResponseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private Double valorNota;
    private Long alunoId;
    private String alunoMatricula;
    private String alunoNome;
    private Long avaliacaoId;
    private String avaliacaoNome;
    private Double avaliacaoPeso;
    private LocalDate avaliacaoData;
    private Long disciplinaId;
    private String disciplinaNome;

    public NotaResponseDTO(){}

    public NotaResponseDTO(Nota nota){
        id = nota.getId();
        valorNota = nota.getValorNota();
        alunoId = nota.getAluno().getId();
        alunoMatricula = nota.getAluno().getMatricula();
        alunoNome = nota.getAluno().getUsuario().getNome();
        avaliacaoId = nota.getAvaliacao().getId();
        avaliacaoNome = nota.getAvaliacao().getNome();
        avaliacaoPeso = nota.getAvaliacao().getPeso();
        avaliacaoData = nota.getAvaliacao().getData();
        disciplinaId = nota.getAvaliacao().getDisciplina().getId();
        disciplinaNome = nota.getAvaliacao().getDisciplina().getNome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValorNota() {
        return valorNota;
    }

    public void setValorNota(Double valorNota) {
        this.valorNota = valorNota;
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

    public Long getAvaliacaoId() {
        return avaliacaoId;
    }

    public void setAvaliacaoId(Long avaliacaoId) {
        this.avaliacaoId = avaliacaoId;
    }

    public String getAvaliacaoNome() {
        return avaliacaoNome;
    }

    public void setAvaliacaoNome(String avaliacaoNome) {
        this.avaliacaoNome = avaliacaoNome;
    }

    public Double getAvaliacaoPeso() {
        return avaliacaoPeso;
    }

    public void setAvaliacaoPeso(Double avaliacaoPeso) {
        this.avaliacaoPeso = avaliacaoPeso;
    }

    public LocalDate getAvaliacaoData() {
        return avaliacaoData;
    }

    public void setAvaliacaoData(LocalDate avaliacaoData) {
        this.avaliacaoData = avaliacaoData;
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
