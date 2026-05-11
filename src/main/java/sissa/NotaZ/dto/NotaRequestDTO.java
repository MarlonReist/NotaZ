package sissa.NotaZ.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class NotaRequestDTO {

    @NotNull(message = "Nota é obrigatória!")
    @DecimalMin(value = "0.0", message = "Nota deve ser maior ou igual a 0!")
    @DecimalMax(value = "10.0", message = "Nota deve ser menor ou igual a 10!")
    private Double valorNota;
    @NotNull(message = "Aluno é obrigatório!")
    private Long alunoId;
    @NotNull(message = "Avaliação é obrigatória!")
    private Long avaliacaoId;

    public NotaRequestDTO(){}

    public NotaRequestDTO(Double valorNota, Long alunoId, Long avaliacaoId) {
        this.valorNota = valorNota;
        this.alunoId = alunoId;
        this.avaliacaoId = avaliacaoId;
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

    public Long getAvaliacaoId() {
        return avaliacaoId;
    }

    public void setAvaliacaoId(Long avaliacaoId) {
        this.avaliacaoId = avaliacaoId;
    }
}
