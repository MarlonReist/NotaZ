package sissa.NotaZ.domain;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Avaliacao implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double peso;
    private LocalDate data;
    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;

    public Avaliacao(){
    }

    public Avaliacao(String nome, Double peso, LocalDate data, Disciplina disciplina) {
        this.nome = nome;
        this.peso = peso;
        this.data = data;
        this.disciplina = disciplina;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Avaliacao avaliacao = (Avaliacao) o;
        return Objects.equals(id, avaliacao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
