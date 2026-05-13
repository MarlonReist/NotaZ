package sissa.NotaZ.services;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sissa.NotaZ.domain.Aluno;
import sissa.NotaZ.domain.Avaliacao;
import sissa.NotaZ.domain.Disciplina;
import sissa.NotaZ.domain.Nota;
import sissa.NotaZ.dto.MediaAlunoDisciplinaResponseDTO;
import sissa.NotaZ.dto.NotaRequestDTO;
import sissa.NotaZ.dto.NotaResponseDTO;
import sissa.NotaZ.repositories.AlunoRepository;
import sissa.NotaZ.repositories.AvaliacaoRepository;
import sissa.NotaZ.repositories.DisciplinaRepository;
import sissa.NotaZ.repositories.NotaRepository;
import sissa.NotaZ.services.exceptions.DatabaseException;
import sissa.NotaZ.services.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotaService {

    private final NotaRepository notaRepository;
    private final AlunoRepository alunoRepository;
    private final AvaliacaoRepository avaliacaoRepository;
    private final DisciplinaRepository disciplinaRepository;

    public NotaService(NotaRepository notaRepository, AlunoRepository alunoRepository, AvaliacaoRepository avaliacaoRepository, DisciplinaRepository disciplinaRepository) {
        this.notaRepository = notaRepository;
        this.alunoRepository = alunoRepository;
        this.avaliacaoRepository = avaliacaoRepository;
        this.disciplinaRepository = disciplinaRepository;
    }

    @Transactional
    public NotaResponseDTO salvar(NotaRequestDTO dto) {
        Aluno aluno = alunoRepository.findById(dto.getAlunoId())
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado. Id: " + dto.getAlunoId()));

        if (!aluno.getUsuario().isAtivo()) {
            throw new DatabaseException("Aluno informado está inativo!");
        }

        Avaliacao avaliacao = avaliacaoRepository.findById(dto.getAvaliacaoId())
                .orElseThrow(() -> new ResourceNotFoundException("Avaliação não encontrada. Id: " + dto.getAvaliacaoId()));

        if (notaRepository.existsByAlunoIdAndAvaliacaoId(aluno.getId(), avaliacao.getId())) {
            throw new DatabaseException("Já existe uma nota lançada para esse aluno nessa avaliação!");
        }

        Nota nota = new Nota();
        nota.setValorNota(dto.getValorNota());
        nota.setAluno(aluno);
        nota.setAvaliacao(avaliacao);
        Nota notaSalva = notaRepository.save(nota);
        return new NotaResponseDTO(notaSalva);
    }

    public NotaResponseDTO buscarPorId(Long id) {
        Nota nota = notaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nota não encontrada. Id: " + id));
        return new NotaResponseDTO(nota);
    }

    public void deletar(Long id) {
        notaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nota não encontrada. Id: " + id));

        notaRepository.deleteById(id);
    }


    public List<NotaResponseDTO> listarTodos() {
        List<Nota> list = notaRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return list.stream().map(NotaResponseDTO::new).collect(Collectors.toList());
    }

    public NotaResponseDTO atualizar(Long id, NotaRequestDTO dto) {
        Nota notaExistente = notaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nota não encontrada. Id: " + id));

        Aluno aluno = alunoRepository.findById(dto.getAlunoId())
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado. Id: " + dto.getAlunoId()));

        if (!aluno.getUsuario().isAtivo()) {
            throw new DatabaseException("Aluno informado está inativo!");
        }

        Avaliacao avaliacao = avaliacaoRepository.findById(dto.getAvaliacaoId())
                .orElseThrow(() -> new ResourceNotFoundException("Avaliação não encontrada. Id: " + dto.getAvaliacaoId()));

        if (notaRepository.existsByAlunoIdAndAvaliacaoIdAndIdNot(aluno.getId(), avaliacao.getId(), id)) {
            throw new DatabaseException("Já existe uma nota lançada para esse aluno nessa avaliação!");
        }

        notaExistente.setValorNota(dto.getValorNota());
        notaExistente.setAluno(aluno);
        notaExistente.setAvaliacao(avaliacao);

        Nota notaSalva = notaRepository.save(notaExistente);
        return new NotaResponseDTO(notaSalva);
    }

    public MediaAlunoDisciplinaResponseDTO calcularMediaAlunoDisciplina(Long alunoId, Long disciplinaId){

        Aluno alunoExistente = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado. Id: " + alunoId));

        if (!alunoExistente.getUsuario().isAtivo()) {
            throw new DatabaseException("Aluno informado está inativo!");
        }

        Disciplina disciplinaExistente = disciplinaRepository.findById(disciplinaId)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada. Id: " + disciplinaId));

        List<Nota> notas = notaRepository.findByAlunoIdAndAvaliacaoDisciplinaId(alunoId, disciplinaId);

        if (notas.isEmpty()) {
            throw new ResourceNotFoundException(("Nenhuma nota encontrada para este aluno nesta disciplina."));
        }

        double somaPonderada = 0.0;
        double totalPeso = 0.0;

        for (Nota nota : notas) {
            double peso = nota.getAvaliacao().getPeso();
            somaPonderada += nota.getValorNota() * peso;
            totalPeso += peso;
        }

        double media = somaPonderada / totalPeso;

        return new MediaAlunoDisciplinaResponseDTO(alunoExistente.getId(), alunoExistente.getUsuario().getNome(), disciplinaExistente.getId(), disciplinaExistente.getNome(), media, totalPeso, notas.size());
    }

}
