package sissa.NotaZ.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProfessorRequestDTO {
    @NotBlank(message = "Ra é obrigatório!")
    private String ra;
    @NotNull(message = "Usuário é obrigatório!")
    private Long usuarioId;

    public ProfessorRequestDTO(){}

    public ProfessorRequestDTO(String ra, Long usuarioId) {
        this.ra = ra;
        this.usuarioId = usuarioId;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
