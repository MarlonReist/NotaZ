package sissa.NotaZ.services;

import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sissa.NotaZ.domain.Turma;
import sissa.NotaZ.domain.Usuario;
import sissa.NotaZ.domain.enums.TipoEnum;
import sissa.NotaZ.dto.TurmaRequestDTO;
import sissa.NotaZ.dto.TurmaResponseDTO;
import sissa.NotaZ.dto.UsuarioRequestDTO;
import sissa.NotaZ.dto.UsuarioResponseDTO;
import sissa.NotaZ.repositories.TurmaRepository;
import sissa.NotaZ.repositories.UsuarioRepository;
import sissa.NotaZ.services.exceptions.DatabaseException;
import sissa.NotaZ.services.exceptions.ResourceNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;

    public TurmaService(TurmaRepository turmaRepository) {
        this.turmaRepository = turmaRepository;
    }


    @Transactional
    public TurmaResponseDTO salvar(TurmaRequestDTO dto) {
        boolean jaExiste = turmaRepository.existsByCursoAndPeriodo(dto.getCurso(), dto.getPeriodo());

        if (jaExiste) throw new DatabaseException("Já existe uma turma para esse curso e período!");
        Turma turma = new Turma();
        turma.setCurso(dto.getCurso());
        turma.setPeriodo(dto.getPeriodo());
        Turma turmaSalva = turmaRepository.save(turma);
        return new TurmaResponseDTO(turmaSalva);
    }

    public TurmaResponseDTO buscarPorId(Long id) {
        Turma turma = turmaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada. Id: " + id));
        return new TurmaResponseDTO(turma);
    }

    public void deletar(Long id) {
        Turma turma = turmaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada. Id: " + id));

        try {
            turmaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(
                    "Não é possível excluir esta Turma, pois ela possui alunos vinculados."
            );
        }
    }


    public List<TurmaResponseDTO> listarTodos() {
        List<Turma> list = turmaRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return list.stream().map(TurmaResponseDTO::new).collect(Collectors.toList());
    }

    public TurmaResponseDTO atualizar(Long id, TurmaRequestDTO dto) {
        Turma turmaExistente = turmaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada. Id: " + id));

        Optional<Turma> encontrada = turmaRepository.findByCursoAndPeriodo(dto.getCurso(),dto.getPeriodo());

        if (encontrada.isPresent() && !encontrada.get().getId().equals(id)) {
            throw new DatabaseException("Essa turma já existe nesse curso e período");
        }

        turmaExistente.setPeriodo(dto.getPeriodo());
        turmaExistente.setCurso(dto.getCurso());

        Turma turmaSalva = turmaRepository.save(turmaExistente);
        return new TurmaResponseDTO(turmaSalva);
    }

}
