package sissa.NotaZ.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class BoletimItemRequestDTO {

    @NotNull(message = "Boletim é obrigatório!")
    private Long boletimId;
    @NotNull(message = "Disciplina é obrigatória!")
    private Long disciplinaId;
    @NotNull(message = "Média é obrigatória!")
    @DecimalMin(value = "0.0", message = "Média deve ser maior ou igual a 0!")
    @DecimalMax(value = "10.0", message = "Média deve ser menor ou igual a 10!")
    private Double media;
    @NotNull(message = "Total de aulas é obrigatório!")
    @PositiveOrZero(message = "Total de aulas deve ser maior ou igual a zero!")
    private Integer totalAulas;
    @NotNull(message = "Total de faltas é obrigatório!")
    @PositiveOrZero(message = "Total de faltas deve ser maior ou igual a zero!")
    private Integer totalFaltas;
    @NotNull(message = "Percentual de frequência é obrigatório!")
    @DecimalMin(value = "0.0", message = "Percentual de frequência deve ser maior ou igual a 0!")
    @DecimalMax(value = "100.0", message = "Percentual de frequência deve ser menor ou igual a 100!")
    private Double percentualFrequencia;

    public BoletimItemRequestDTO(){
    }

    public BoletimItemRequestDTO(Long boletimId, Long disciplinaId, Double media, Integer totalAulas, Integer totalFaltas, Double percentualFrequencia) {
        this.boletimId = boletimId;
        this.disciplinaId = disciplinaId;
        this.media = media;
        this.totalAulas = totalAulas;
        this.totalFaltas = totalFaltas;
        this.percentualFrequencia = percentualFrequencia;
    }

    public Long getBoletimId() {
        return boletimId;
    }

    public void setBoletimId(Long boletimId) {
        this.boletimId = boletimId;
    }

    public Long getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(Long disciplinaId) {
        this.disciplinaId = disciplinaId;
    }

    public Double getMedia() {
        return media;
    }

    public void setMedia(Double media) {
        this.media = media;
    }

    public Integer getTotalAulas() {
        return totalAulas;
    }

    public void setTotalAulas(Integer totalAulas) {
        this.totalAulas = totalAulas;
    }

    public Integer getTotalFaltas() {
        return totalFaltas;
    }

    public void setTotalFaltas(Integer totalFaltas) {
        this.totalFaltas = totalFaltas;
    }

    public Double getPercentualFrequencia() {
        return percentualFrequencia;
    }

    public void setPercentualFrequencia(Double percentualFrequencia) {
        this.percentualFrequencia = percentualFrequencia;
    }

}
