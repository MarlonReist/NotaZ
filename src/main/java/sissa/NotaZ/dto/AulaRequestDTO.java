package sissa.NotaZ.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class AulaRequestDTO {

    @NotNull(message = "Data é obrigatória!")
    private LocalDate data;
    @NotNull(message = "Quantidade de aulas é obrigatória!")
    @Positive(message = "Quantidade de aulas deve ser maior que zero!")
    private Integer quantidadeAulas;
    @NotNull(message = "Disciplina é obrigatória!")
    private Long disciplinaId;
    @NotNull(message = "Turma é obrigatória!")
    private Long turmaId;

    public AulaRequestDTO(){
    }

    public AulaRequestDTO(LocalDate data, Integer quantidadeAulas, Long disciplinaId, Long turmaId) {
        this.data = data;
        this.quantidadeAulas = quantidadeAulas;
        this.disciplinaId = disciplinaId;
        this.turmaId = turmaId;
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

    public Long getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(Long disciplinaId) {
        this.disciplinaId = disciplinaId;
    }

    public Long getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(Long turmaId) {
        this.turmaId = turmaId;
    }
}
