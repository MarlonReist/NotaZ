package sissa.NotaZ.dto;

import sissa.NotaZ.domain.Turma;
import sissa.NotaZ.domain.enums.CursoEnum;
import sissa.NotaZ.domain.enums.PeriodoEnum;

import java.io.Serial;
import java.io.Serializable;

public class TurmaResponseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private PeriodoEnum periodo;
    private CursoEnum curso;

    public TurmaResponseDTO() {
    }

    public TurmaResponseDTO(Turma turma) {
        id = turma.getId();
        periodo = turma.getPeriodo();
        curso = turma.getCurso();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
