package sissa.NotaZ.domain;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Nota implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double valorNota;
    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;
    @ManyToOne
    @JoinColumn(name = "avaliacao_id")
    private Avaliacao avaliacao;

    public Nota(){
    }

    public Nota(Double valorNota, Aluno aluno, Avaliacao avaliacao) {
        this.valorNota = valorNota;
        this.aluno = aluno;
        this.avaliacao = avaliacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValorNota() {
        return valorNota;
    }

    public void setValorNota(Double valorNota) {
        this.valorNota = valorNota;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Nota nota = (Nota) o;
        return Objects.equals(id, nota.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
