package sissa.NotaZ.domain;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Aula implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data;
    private Integer quantidadeAulas;
    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;
    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;

    public Aula(){
    }

    public Aula(LocalDate data, Integer quantidadeAulas, Disciplina disciplina, Turma turma) {
        this.data = data;
        this.quantidadeAulas = quantidadeAulas;
        this.disciplina = disciplina;
        this.turma = turma;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Aula aula = (Aula) o;
        return Objects.equals(id, aula.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
