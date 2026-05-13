package sissa.NotaZ.services;

import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sissa.NotaZ.domain.Aula;
import sissa.NotaZ.domain.Disciplina;
import sissa.NotaZ.domain.Turma;
import sissa.NotaZ.dto.AulaRequestDTO;
import sissa.NotaZ.dto.AulaResponseDTO;
import sissa.NotaZ.repositories.AulaRepository;
import sissa.NotaZ.repositories.DisciplinaRepository;
import sissa.NotaZ.repositories.TurmaRepository;
import sissa.NotaZ.services.exceptions.DatabaseException;
import sissa.NotaZ.services.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AulaService {

    private final AulaRepository aulaRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final TurmaRepository turmaRepository;

    public AulaService(AulaRepository aulaRepository, DisciplinaRepository disciplinaRepository, TurmaRepository turmaRepository) {
        this.aulaRepository = aulaRepository;
        this.disciplinaRepository = disciplinaRepository;
        this.turmaRepository = turmaRepository;
    }

    @Transactional
    public AulaResponseDTO salvar(AulaRequestDTO dto) {
        Disciplina disciplina = disciplinaRepository.findById(dto.getDisciplinaId())
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada. Id: " + dto.getDisciplinaId()));

        if (!disciplina.getProfessor().getUsuario().isAtivo()) {
            throw new DatabaseException("Professor da disciplina está inativo!");
        }

        Turma turma = turmaRepository.findById(dto.getTurmaId())
                .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada. Id: " + dto.getTurmaId()));

        if (aulaRepository.existsByTurmaIdAndDisciplinaIdAndData(turma.getId(), disciplina.getId(), dto.getData())) {
            throw new DatabaseException("Já existe aula para esta turma, disciplina e data!");
        }

        Aula aula = new Aula();
        aula.setData(dto.getData());
        aula.setQuantidadeAulas(dto.getQuantidadeAulas());
        aula.setDisciplina(disciplina);
        aula.setTurma(turma);

        Aula aulaSalva = aulaRepository.save(aula);
        return new AulaResponseDTO(aulaSalva);
    }

    public AulaResponseDTO buscarPorId(Long id) {
        Aula aula = aulaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aula não encontrada. Id: " + id));
        return new AulaResponseDTO(aula);
    }

    @Transactional
    public void deletar(Long id) {
        aulaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aula não encontrada. Id: " + id));

        try {
            aulaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(
                    "Não é possível excluir esta Aula, pois ela possui frequências vinculadas!"
            );
        }
    }

    public List<AulaResponseDTO> listarTodos() {
        List<Aula> list = aulaRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return list.stream().map(AulaResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public AulaResponseDTO atualizar(Long id, AulaRequestDTO dto) {
        Aula aula = aulaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aula não encontrada. Id: " + id));

        Disciplina disciplina = disciplinaRepository.findById(dto.getDisciplinaId())
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada. Id: " + dto.getDisciplinaId()));

        if (!disciplina.getProfessor().getUsuario().isAtivo()) {
            throw new DatabaseException("Professor da disciplina está inativo!");
        }

        Turma turma = turmaRepository.findById(dto.getTurmaId())
                .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada. Id: " + dto.getTurmaId()));

        if (aulaRepository.existsByTurmaIdAndDisciplinaIdAndDataAndIdNot(turma.getId(), disciplina.getId(), dto.getData(), id)) {
            throw new DatabaseException("Já existe aula para esta turma, disciplina e data!");
        }

        aula.setData(dto.getData());
        aula.setQuantidadeAulas(dto.getQuantidadeAulas());
        aula.setDisciplina(disciplina);
        aula.setTurma(turma);

        Aula aulaSalva = aulaRepository.save(aula);
        return new AulaResponseDTO(aulaSalva);
    }
}
