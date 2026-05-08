package sissa.NotaZ.dto;

import jakarta.validation.constraints.NotNull;
import sissa.NotaZ.domain.enums.CursoEnum;
import sissa.NotaZ.domain.enums.PeriodoEnum;

public class TurmaRequestDTO {
    @NotNull(message = "Período é obrigatório!")
    private PeriodoEnum periodo;
    @NotNull(message = "Curso é obrigatório!")
    private CursoEnum curso;

    public TurmaRequestDTO(){}

    public TurmaRequestDTO(PeriodoEnum periodo, CursoEnum curso) {
        this.periodo = periodo;
        this.curso = curso;
    }

    public PeriodoEnum getPeriodo() {
        return periodo;
    }

    public void setPeriodo(PeriodoEnum periodo) {
        this.periodo = periodo;
    }

    public CursoEnum getCurso() {
        return curso;
    }

    public void setCurso(CursoEnum curso) {
        this.curso = curso;
    }
}
