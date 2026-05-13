package sissa.NotaZ.services;

import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sissa.NotaZ.domain.Aluno;
import sissa.NotaZ.domain.Turma;
import sissa.NotaZ.domain.Usuario;
import sissa.NotaZ.domain.enums.TipoEnum;
import sissa.NotaZ.dto.AlunoRequestDTO;
import sissa.NotaZ.dto.AlunoResponseDTO;
import sissa.NotaZ.repositories.AlunoRepository;
import sissa.NotaZ.repositories.TurmaRepository;
import sissa.NotaZ.repositories.UsuarioRepository;
import sissa.NotaZ.services.exceptions.DatabaseException;
import sissa.NotaZ.services.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final TurmaRepository turmaRepository;
    private final UsuarioRepository usuarioRepository;

    public AlunoService(AlunoRepository alunoRepository, TurmaRepository turmaRepository, UsuarioRepository usuarioRepository) {
        this.alunoRepository = alunoRepository;
        this.turmaRepository = turmaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public AlunoResponseDTO salvar(AlunoRequestDTO dto) {
        if (alunoRepository.existsByMatricula((dto.getMatricula()))) {
            throw new DatabaseException(("Matricula já existe!"));
        }

        Turma turma = turmaRepository.findById(dto.getTurmaId())
                .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada. Id: " + dto.getTurmaId()));

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado. Id: " + dto.getUsuarioId()));

        if (usuario.getTipo() != TipoEnum.ALUNO) {
            throw new DatabaseException("Usuário informado não é do tipo ALUNO");
        }

        if (!usuario.isAtivo()) {
            throw new DatabaseException("Usuário informado está inativo!");
        }

        if (alunoRepository.existsByUsuarioId(dto.getUsuarioId())) {
            throw new DatabaseException("Usuário já está vinculado a um aluno!");
        }

        Aluno aluno = new Aluno();
        aluno.setMatricula(dto.getMatricula());
        aluno.setTurma(turma);
        aluno.setDataNascimento(dto.getDataNascimento());
        aluno.setUsuario(usuario);
        Aluno alunoSalvo = alunoRepository.save(aluno);
        return new AlunoResponseDTO(alunoSalvo);
    }

    public AlunoResponseDTO buscarPorId(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado. Id: " + id));
        return new AlunoResponseDTO(aluno);
    }

    public void deletar(Long id) {
        alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado. Id: " + id));

        try {
            alunoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(
                    "Não é possível excluir este Aluno, pois ele possui vínculos ao sistema."
            );
        }
    }


    public List<AlunoResponseDTO> listarTodos() {
        List<Aluno> list = alunoRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return list.stream().map(AlunoResponseDTO::new).collect(Collectors.toList());
    }

    public AlunoResponseDTO atualizar(Long id, AlunoRequestDTO dto) {
        Optional<Aluno> encontrado = alunoRepository.findByMatricula(dto.getMatricula());

        if (encontrado.isPresent() && !encontrado.get().getId().equals(id)) {
            throw new DatabaseException("Matricula já é de outro aluno!");
        }

        Aluno alunoExistente = alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado. Id: " + id));

        Turma turma = turmaRepository.findById(dto.getTurmaId())
                .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada. Id: " + dto.getTurmaId()));

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado. Id: " + dto.getUsuarioId()));

        if (usuario.getTipo() != TipoEnum.ALUNO) {
            throw new DatabaseException("Usuário informado não é do tipo ALUNO");
        }

        if (!usuario.isAtivo()) {
            throw new DatabaseException("Usuário informado está inativo!");
        }

        boolean trocandoUsuario = !dto.getUsuarioId().equals(alunoExistente.getUsuario().getId());

        if (trocandoUsuario && alunoRepository.existsByUsuarioId(dto.getUsuarioId())) {
            throw new DatabaseException("Usuário já está vinculado a outro aluno!");
        }

        alunoExistente.setUsuario(usuario);
        alunoExistente.setTurma(turma);
        alunoExistente.setMatricula(dto.getMatricula());
        alunoExistente.setDataNascimento(dto.getDataNascimento());

        Aluno alunoSalvo = alunoRepository.save(alunoExistente);
        return new AlunoResponseDTO(alunoSalvo);
    }

}
