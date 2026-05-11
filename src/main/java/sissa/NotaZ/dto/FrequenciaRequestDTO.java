package sissa.NotaZ.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class FrequenciaRequestDTO {

    @NotNull(message = "Data é obrigatória!")
    private LocalDate data;
    @NotNull(message = "Presença é obrigatória!")
    private Boolean presente;
    @NotNull(message = "Aluno é obrigatório!")
    private Long alunoId;
    @NotNull(message = "Disciplina é obrigatória!")
    private Long disciplinaId;

    public FrequenciaRequestDTO(){
    }

    public FrequenciaRequestDTO(LocalDate data, Boolean presente, Long alunoId, Long disciplinaId) {
        this.data = data;
        this.presente = presente;
        this.alunoId = alunoId;
        this.disciplinaId = disciplinaId;
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

    public Long getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(Long disciplinaId) {
        this.disciplinaId = disciplinaId;
    }
}
