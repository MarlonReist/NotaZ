package sissa.NotaZ.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class AlunoRequestDTO {
    @NotBlank(message = "Matricula é obrigatória!")
    private String matricula;
    @NotNull(message = "Data de nascimento é obrigatória!")
    private LocalDate dataNascimento;
    @NotNull(message = "Usuário é obrigatório!")
    private Long usuarioId;
    @NotNull(message = "Turma é obrigatório!")
    private Long turmaId;

    public AlunoRequestDTO() {
    }

    public AlunoRequestDTO(String matricula, LocalDate dataNascimento, Long usuarioId, Long turmaId) {
        this.matricula = matricula;
        this.dataNascimento = dataNascimento;
        this.usuarioId = usuarioId;
        this.turmaId = turmaId;
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

    public Long getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(Long turmaId) {
        this.turmaId = turmaId;
    }
}
