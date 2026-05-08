package sissa.NotaZ.services;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import sissa.NotaZ.domain.Usuario;
import sissa.NotaZ.services.exceptions.DatabaseException;
import sissa.NotaZ.services.exceptions.ResourceNotFoundException;
import sissa.NotaZ.dto.UsuarioRequestDTO;
import sissa.NotaZ.dto.UsuarioResponseDTO;
import sissa.NotaZ.repositories.UsuarioRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public UsuarioResponseDTO salvar(UsuarioRequestDTO dto){
        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new DatabaseException();
        }


        Usuario usuario = new Usuario();
        usuario.setDataCriacao(LocalDate.now());
        usuario.setEmail(dto.getEmail());
        usuario.setNome(dto.getNome());
        usuario.setSenha(dto.getSenha());
        usuario.setTipo(dto.getTipo());
        usuario.setAtivo(true);
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return new UsuarioResponseDTO(usuarioSalvo);
    }

    public UsuarioResponseDTO buscarPorId(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        return new UsuarioResponseDTO(usuario);
    }

    public void deletar(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        if (usuario.isAtivo()) {
            throw new DatabaseException("Não é possível deletar usuário ativo!");
        }

        usuarioRepository.deleteById(id);
    }

    public List<UsuarioResponseDTO> listarTodos(){
    List<Usuario> list = usuarioRepository.findAll();
    return list.stream().map(UsuarioResponseDTO::new).collect(Collectors.toList());
    }

    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO dto){
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        Usuario outroUsuario = usuarioRepository.findByEmail(dto.getEmail()).orElse(null);

        if (outroUsuario == null || outroUsuario.getId().equals(usuarioExistente.getId())){
            usuarioExistente.setNome(dto.getNome());
            usuarioExistente.setSenha(dto.getSenha());
            usuarioExistente.setEmail(dto.getEmail());

            Usuario usuarioSalvo = usuarioRepository.save(usuarioExistente);
            return new UsuarioResponseDTO(usuarioSalvo);
        }
        throw new DatabaseException();
    }

    public UsuarioResponseDTO desativar(Long id){
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        usuarioExistente.setAtivo(false);
        Usuario usuarioSalvo = usuarioRepository.save(usuarioExistente);
        return new UsuarioResponseDTO(usuarioSalvo);
    }

    public UsuarioResponseDTO ativar(Long id){
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        usuarioExistente.setAtivo(true);
        Usuario usuarioSalvo = usuarioRepository.save(usuarioExistente);
        return new UsuarioResponseDTO(usuarioSalvo);
    }

}
