package sissa.NotaZ.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class BoletimRequestDTO {

    @NotBlank(message = "Período é obrigatório!")
    private String periodo;
    @NotNull(message = "Data de fechamento é obrigatória!")
    private LocalDate dataFechamento;
    @NotNull(message = "Média geral é obrigatória!")
    @DecimalMin(value = "0.0", message = "Média geral deve ser maior ou igual a 0!")
    @DecimalMax(value = "10.0", message = "Média geral deve ser menor ou igual a 10!")
    private Double mediaGeral;
    @NotNull(message = "Percentual de frequência é obrigatório!")
    @DecimalMin(value = "0.0", message = "Percentual de frequência deve ser maior ou igual a 0!")
    @DecimalMax(value = "100.0", message = "Percentual de frequência deve ser menor ou igual a 100!")
    private Double percentualFrequencia;
    @NotBlank(message = "Situação é obrigatória!")
    private String situacao;
    @NotNull(message = "Aluno é obrigatório!")
    private Long alunoId;
    @NotNull(message = "Turma é obrigatória!")
    private Long turmaId;

    public BoletimRequestDTO(){
    }

    public BoletimRequestDTO(String periodo, LocalDate dataFechamento, Double mediaGeral, Double percentualFrequencia, String situacao, Long alunoId, Long turmaId) {
        this.periodo = periodo;
        this.dataFechamento = dataFechamento;
        this.mediaGeral = mediaGeral;
        this.percentualFrequencia = percentualFrequencia;
        this.situacao = situacao;
        this.alunoId = alunoId;
        this.turmaId = turmaId;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public LocalDate getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Double getMediaGeral() {
        return mediaGeral;
    }

    public void setMediaGeral(Double mediaGeral) {
        this.mediaGeral = mediaGeral;
    }

    public Double getPercentualFrequencia() {
        return percentualFrequencia;
    }

    public void setPercentualFrequencia(Double percentualFrequencia) {
        this.percentualFrequencia = percentualFrequencia;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public Long getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(Long turmaId) {
        this.turmaId = turmaId;
    }
}
