package sissa.NotaZ.dto;

import jakarta.validation.constraints.*;
import sissa.NotaZ.domain.enums.TipoEnum;

public class UsuarioRequestDTO {
    @NotBlank(message = "Nome é obrigatório!")
    private String nome;
    @NotBlank(message = "Email é obrigatório!")
    @Email
    private String email;
    @NotBlank(message = "Senha é obrigatória!")
    @Size(min = 8, message = "Senha precisa ter no mínimo 8 caracteres")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d).+$",
            message = "A senha deve conter pelo menos uma letra maiúscula e um número"
    )
    private String senha;
    @NotNull(message = "Tipo de usuário é obrigatório")
    private TipoEnum tipo;

    public UsuarioRequestDTO(){
    }

    public UsuarioRequestDTO(String nome, String email, String senha, TipoEnum tipo) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoEnum tipo) {
        this.tipo = tipo;
    }
}
