package sissa.NotaZ.services;

import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sissa.NotaZ.domain.Avaliacao;
import sissa.NotaZ.domain.Disciplina;
import sissa.NotaZ.dto.AvaliacaoResponseDTO;
import sissa.NotaZ.dto.AvaliacaoRequestDTO;
import sissa.NotaZ.repositories.AvaliacaoRepository;
import sissa.NotaZ.repositories.DisciplinaRepository;
import sissa.NotaZ.services.exceptions.DatabaseException;
import sissa.NotaZ.services.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;
    private final DisciplinaRepository disciplinaRepository;

    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository, DisciplinaRepository disciplinaRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.disciplinaRepository = disciplinaRepository;
    }

    @Transactional
    public AvaliacaoResponseDTO salvar(AvaliacaoRequestDTO dto) {
        Disciplina disciplina = disciplinaRepository.findById(dto.getDisciplinaId())
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada. Id: " + dto.getDisciplinaId()));

        if (avaliacaoRepository.existsByDisciplinaIdAndNomeAndData(disciplina.getId(), dto.getNome(), dto.getData())) {
            throw new DatabaseException("Já existe avaliação com esse nome nessa disciplina nessa data");
        }

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNome(dto.getNome());
        avaliacao.setPeso(dto.getPeso());
        avaliacao.setData(dto.getData());
        avaliacao.setDisciplina(disciplina);
        Avaliacao avaliacaoSalva = avaliacaoRepository.save(avaliacao);
        return new AvaliacaoResponseDTO(avaliacaoSalva);
    }

    public AvaliacaoResponseDTO buscarPorId(Long id) {
        Avaliacao avaliacao = avaliacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Avaliação não encontrada. Id: " + id));
        return new AvaliacaoResponseDTO(avaliacao);
    }

    public void deletar(Long id) {
        avaliacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Avaliação não encontrada. Id: " + id));

        try {
            avaliacaoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(
                    "Não é possível excluir esta Avaliação, pois ela possui Notas vinculadas!"
            );
        }
    }


    public List<AvaliacaoResponseDTO> listarTodos() {
        List<Avaliacao> list = avaliacaoRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return list.stream().map(AvaliacaoResponseDTO::new).collect(Collectors.toList());
    }

    public AvaliacaoResponseDTO atualizar(Long id, AvaliacaoRequestDTO dto) {
        Avaliacao avaliacao = avaliacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Avaliação não encontrada. Id: " + id));

        Long disciplinaId = avaliacao.getDisciplina().getId();

        if (avaliacaoRepository.existsByDisciplinaIdAndNomeAndDataAndIdNot(disciplinaId, dto.getNome(), dto.getData(), id)) {
            throw new DatabaseException("Já existe avaliação com esse nome nessa disciplina nessa data");
        }

        avaliacao.setNome(dto.getNome());
        avaliacao.setPeso(dto.getPeso());
        avaliacao.setData(dto.getData());

        Avaliacao avaliacaoSalva = avaliacaoRepository.save(avaliacao);
        return new AvaliacaoResponseDTO(avaliacaoSalva);
    }

}
