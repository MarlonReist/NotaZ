package sissa.NotaZ.domain;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Aluno implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String matricula;
    private LocalDate dataNascimento;
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;

    public Aluno(){
    }

    public Aluno(String matricula, LocalDate dataNascimento, Usuario usuario, Turma turma) {
        this.matricula = matricula;
        this.dataNascimento = dataNascimento;
        this.usuario = usuario;
        this.turma = turma;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        Aluno aluno = (Aluno) o;
        return Objects.equals(id, aluno.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
