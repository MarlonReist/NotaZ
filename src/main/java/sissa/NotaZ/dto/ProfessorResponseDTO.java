package sissa.NotaZ.dto;

import sissa.NotaZ.domain.Professor;

import java.io.Serial;
import java.io.Serializable;

public class ProfessorResponseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String ra;
    private Long usuarioId;
    private String usuarioNome;
    private String usuarioEmail;

    public ProfessorResponseDTO(){
    }

    public ProfessorResponseDTO(Professor professor){
        id = professor.getId();
        ra = professor.getRa();
        usuarioId = professor.getUsuario().getId();
        usuarioNome = professor.getUsuario().getNome();
        usuarioEmail = professor.getUsuario().getEmail();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUsuarioNome() {
        return usuarioNome;
    }

    public void setUsuarioNome(String usuarioNome) {
        this.usuarioNome = usuarioNome;
    }

    public String getUsuarioEmail() {
        return usuarioEmail;
    }

    public void setUsuarioEmail(String usuarioEmail) {
        this.usuarioEmail = usuarioEmail;
    }
}
