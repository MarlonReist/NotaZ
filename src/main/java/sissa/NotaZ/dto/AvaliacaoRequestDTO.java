package sissa.NotaZ.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class AvaliacaoRequestDTO {
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    @NotNull(message = "Peso é obrigatório!")
    @Positive
    private Double peso;
    @NotNull(message = "Data é obrigatória!")
    private LocalDate data;
    @NotNull(message = "Disciplina é obrigatória!")
    private Long disciplinaId;

    public AvaliacaoRequestDTO(){}

    public AvaliacaoRequestDTO(String nome, Double peso, LocalDate data, Long disciplinaId) {
        this.nome = nome;
        this.peso = peso;
        this.data = data;
        this.disciplinaId = disciplinaId;
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
}
