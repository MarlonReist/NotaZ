package sissa.NotaZ.dto;

import jakarta.validation.constraints.NotNull;

public class FrequenciaRequestDTO {

    @NotNull(message = "Presença é obrigatória!")
    private Boolean presente;
    @NotNull(message = "Aluno é obrigatório!")
    private Long alunoId;
    @NotNull(message = "Aula é obrigatória!")
    private Long aulaId;

    public FrequenciaRequestDTO(){
    }

    public FrequenciaRequestDTO(Boolean presente, Long alunoId, Long aulaId) {
        this.presente = presente;
        this.alunoId = alunoId;
        this.aulaId = aulaId;
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

    public Long getAulaId() {
        return aulaId;
    }

    public void setAulaId(Long aulaId) {
        this.aulaId = aulaId;
    }
}
