package sissa.NotaZ.domain;

import jakarta.persistence.*;
import sissa.NotaZ.domain.enums.CursoEnum;
import sissa.NotaZ.domain.enums.PeriodoEnum;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Turma implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private PeriodoEnum periodo;
    @Enumerated(EnumType.STRING)
    private CursoEnum curso;

    public Turma(){
    }

    public Turma(PeriodoEnum periodo, CursoEnum curso) {
        this.periodo = periodo;
        this.curso = curso;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Turma turma = (Turma) o;
        return Objects.equals(id, turma.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
