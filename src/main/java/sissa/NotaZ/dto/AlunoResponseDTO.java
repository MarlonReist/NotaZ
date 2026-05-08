package sissa.NotaZ.dto;

import sissa.NotaZ.domain.Aluno;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class AlunoResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String matricula;
    private LocalDate dataNascimento;
    private Long usuarioId;
    private String usuarioNome;
    private Long turmaId;

    public AlunoResponseDTO() {
    }

    public AlunoResponseDTO(Aluno aluno) {
        id = aluno.getId();
        matricula = aluno.getMatricula();
        dataNascimento = aluno.getDataNascimento();
        usuarioId = aluno.getUsuario().getId();
        usuarioNome = aluno.getUsuario().getNome();
        turmaId = aluno.getTurma().getId();
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

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioNome() {
        return usuarioNome;
    }

    public void setUsuarioNome(String usuarioNome) {
        this.usuarioNome = usuarioNome;
    }

    public Long getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(Long turmaId) {
        this.turmaId = turmaId;
    }
}
