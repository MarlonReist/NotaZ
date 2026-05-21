package sissa.NotaZ.services;

import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sissa.NotaZ.domain.Professor;
import sissa.NotaZ.domain.Usuario;
import sissa.NotaZ.domain.enums.TipoEnum;
import sissa.NotaZ.dto.ProfessorRequestDTO;
import sissa.NotaZ.dto.ProfessorResponseDTO;
import sissa.NotaZ.repositories.ProfessorRepository;
import sissa.NotaZ.repositories.UsuarioRepository;
import sissa.NotaZ.services.exceptions.DatabaseException;
import sissa.NotaZ.services.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final UsuarioRepository usuarioRepository;

    public ProfessorService(ProfessorRepository professorRepository, UsuarioRepository usuarioRepository) {
        this.professorRepository = professorRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public ProfessorResponseDTO salvar(ProfessorRequestDTO dto) {
        if (professorRepository.existsByRa((dto.getRa()))) {
            throw new DatabaseException(("Ra já existe!"));
        }

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado. Id: " + dto.getUsuarioId()));

        if (usuario.getTipo() != TipoEnum.PROFESSOR) {
            throw new DatabaseException("Usuário informado não é do tipo PROFESSOR");
        }

        if (!usuario.isAtivo()) {
            throw new DatabaseException("Usuário informado está inativo!");
        }

        if (professorRepository.existsByUsuarioId(dto.getUsuarioId())) {
            throw new DatabaseException("Usuário já está vinculado a um professor!");
        }

        Professor professor = new Professor();
        professor.setRa(dto.getRa());
        professor.setUsuario(usuario);
        Professor professorSalvo = professorRepository.save(professor);
        return new ProfessorResponseDTO(professorSalvo);
    }

    public ProfessorResponseDTO buscarPorId(Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado. Id: " + id));
        return new ProfessorResponseDTO(professor);
    }

    public void deletar(Long id) {
        professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado. Id: " + id));

        try {
            professorRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(
                    "Não é possível excluir este Professor, pois ele possui vínculos ao sistema."
            );
        }
    }


    public List<ProfessorResponseDTO> listarTodos() {
        List<Professor> list = professorRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return list.stream().map(ProfessorResponseDTO::new).collect(Collectors.toList());
    }

    public ProfessorResponseDTO atualizar(Long id, ProfessorRequestDTO dto) {
        Optional<Professor> encontrado = professorRepository.findByRa(dto.getRa());

        if (encontrado.isPresent() && !encontrado.get().getId().equals(id)) {
            throw new DatabaseException("Ra já é de outro professor!");
        }

        Professor professorExistente = professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado. Id: " + id));

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado. Id: " + dto.getUsuarioId()));

        if (usuario.getTipo() != TipoEnum.PROFESSOR) {
            throw new DatabaseException("Usuário informado não é do tipo PROFESSOR");
        }

        if (!usuario.isAtivo()) {
            throw new DatabaseException("Usuário informado está inativo!");
        }

        boolean trocandoUsuario = !dto.getUsuarioId().equals(professorExistente.getUsuario().getId());

        if (trocandoUsuario && professorRepository.existsByUsuarioId(dto.getUsuarioId())) {
            throw new DatabaseException("Usuário já está vinculado a outro professor!");
        }

        professorExistente.setUsuario(usuario);
        professorExistente.setRa(dto.getRa());

        Professor professorSalvo = professorRepository.save(professorExistente);
        return new ProfessorResponseDTO(professorSalvo);
    }

}
