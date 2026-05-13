package sissa.NotaZ.dto;

import sissa.NotaZ.domain.Usuario;
import sissa.NotaZ.domain.enums.TipoEnum;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class UsuarioResponseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String email;
    private TipoEnum tipo;
    private LocalDate dataCriacao;
    private Boolean ativo;

    public UsuarioResponseDTO(){
    }

    public UsuarioResponseDTO(Usuario usuario){
        id = usuario.getId();
        nome = usuario.getNome();
        email = usuario.getEmail();
        tipo = usuario.getTipo();
        dataCriacao = usuario.getDataCriacao();
        ativo = usuario.isAtivo();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoEnum tipo) {
        this.tipo = tipo;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
