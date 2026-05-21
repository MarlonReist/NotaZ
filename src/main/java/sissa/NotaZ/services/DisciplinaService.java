package sissa.NotaZ.services;

import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sissa.NotaZ.domain.*;
import sissa.NotaZ.dto.AlunoResponseDTO;
import sissa.NotaZ.dto.DisciplinaRequestDTO;
import sissa.NotaZ.dto.DisciplinaResponseDTO;
import sissa.NotaZ.repositories.*;
import sissa.NotaZ.services.exceptions.DatabaseException;
import sissa.NotaZ.services.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;
    private final ProfessorRepository professorRepository;

    public DisciplinaService(DisciplinaRepository disciplinaRepository, ProfessorRepository professorRepository) {
        this.disciplinaRepository = disciplinaRepository;
        this.professorRepository = professorRepository;
    }

    @Transactional
    public DisciplinaResponseDTO salvar(DisciplinaRequestDTO dto) {
        Professor professor = professorRepository.findById(dto.getProfessorId())
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado. Id: " + dto.getProfessorId()));

        if (!professor.getUsuario().isAtivo()) {
            throw new DatabaseException("Professor informado está inativo!");
        }

        if (disciplinaRepository.existsByProfessorIdAndNome(dto.getProfessorId(), dto.getNome())) {
            throw new DatabaseException(("Já existe disciplina com esse nome para este professor!"));
        }

        Disciplina disciplina = new Disciplina();
        disciplina.setNome(dto.getNome());
        disciplina.setProfessor(professor);
        Disciplina disciplinaSalva = disciplinaRepository.save(disciplina);
        return new DisciplinaResponseDTO(disciplinaSalva);
    }

    public DisciplinaResponseDTO buscarPorId(Long id) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada. Id: " + id));
        return new DisciplinaResponseDTO(disciplina);
    }

    public void deletar(Long id) {
        disciplinaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada. Id: " + id));

        try {
            disciplinaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(
                    "Não é possível excluir esta Disciplina, pois ela possui avaliações vinculadas!"
            );
        }
    }


    public List<DisciplinaResponseDTO> listarTodos() {
        List<Disciplina> list = disciplinaRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return list.stream().map(DisciplinaResponseDTO::new).collect(Collectors.toList());
    }

    public DisciplinaResponseDTO atualizar(Long id, DisciplinaRequestDTO dto) {
        Disciplina disciplinaExistente = disciplinaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada. Id: " + id));

        Professor professor = professorRepository.findById(dto.getProfessorId())
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado. Id: " + dto.getProfessorId()));

        if (!professor.getUsuario().isAtivo()) {
            throw new DatabaseException("Professor informado está inativo!");
        }

        Optional<Disciplina> encontrada = disciplinaRepository.findByProfessorIdAndNome(
                dto.getProfessorId(), dto.getNome());
        if (encontrada.isPresent() && !encontrada.get().getId().equals(id)) {
            throw new DatabaseException("Já existe disciplina com esse nome para este professor!");
        }
        disciplinaExistente.setNome(dto.getNome());
        disciplinaExistente.setProfessor(professor);

        Disciplina disciplinaSalva = disciplinaRepository.save(disciplinaExistente);
        return new DisciplinaResponseDTO(disciplinaSalva);
    }

}
