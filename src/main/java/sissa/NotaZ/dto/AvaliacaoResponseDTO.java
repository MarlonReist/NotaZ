package sissa.NotaZ.dto;

import sissa.NotaZ.domain.Avaliacao;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class AvaliacaoResponseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private Double peso;
    private LocalDate data;
    private Long disciplinaId;
    private String disciplinaNome;

    public AvaliacaoResponseDTO(){}

    public AvaliacaoResponseDTO(Avaliacao avaliacao){
        id = avaliacao.getId();
        nome = avaliacao.getNome();
        peso = avaliacao.getPeso();
        data = avaliacao.getData();
        disciplinaId = avaliacao.getDisciplina().getId();
        disciplinaNome = avaliacao.getDisciplina().getNome();
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

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
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
