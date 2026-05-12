package sissa.NotaZ.services;

import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sissa.NotaZ.domain.Boletim;
import sissa.NotaZ.domain.BoletimItem;
import sissa.NotaZ.domain.Disciplina;
import sissa.NotaZ.dto.BoletimItemRequestDTO;
import sissa.NotaZ.dto.BoletimItemResponseDTO;
import sissa.NotaZ.repositories.BoletimItemRepository;
import sissa.NotaZ.repositories.BoletimRepository;
import sissa.NotaZ.repositories.DisciplinaRepository;
import sissa.NotaZ.services.exceptions.DatabaseException;
import sissa.NotaZ.services.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoletimItemService {

    private static final double MEDIA_MINIMA = 6.0;
    private static final double FREQUENCIA_MINIMA = 70.0;

    private final BoletimItemRepository boletimItemRepository;
    private final BoletimRepository boletimRepository;
    private final DisciplinaRepository disciplinaRepository;

    public BoletimItemService(BoletimItemRepository boletimItemRepository, BoletimRepository boletimRepository, DisciplinaRepository disciplinaRepository) {
        this.boletimItemRepository = boletimItemRepository;
        this.boletimRepository = boletimRepository;
        this.disciplinaRepository = disciplinaRepository;
    }

    @Transactional
    public BoletimItemResponseDTO salvar(BoletimItemRequestDTO dto) {
        Boletim boletim = boletimRepository.findById(dto.getBoletimId())
                .orElseThrow(() -> new ResourceNotFoundException("Boletim não encontrado. Id: " + dto.getBoletimId()));

        Disciplina disciplina = disciplinaRepository.findById(dto.getDisciplinaId())
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada. Id: " + dto.getDisciplinaId()));

        if (boletimItemRepository.existsByBoletimIdAndDisciplinaId(boletim.getId(), disciplina.getId())) {
            throw new DatabaseException("Já existe item de boletim para esta disciplina neste boletim!");
        }

        BoletimItem boletimItem = new BoletimItem();
        boletimItem.setBoletim(boletim);
        boletimItem.setDisciplina(disciplina);
        boletimItem.setMedia(dto.getMedia());
        boletimItem.setTotalAulas(dto.getTotalAulas());
        boletimItem.setTotalFaltas(dto.getTotalFaltas());
        boletimItem.setPercentualFrequencia(dto.getPercentualFrequencia());
        boletimItem.setSituacao(definirSituacao(dto.getMedia(), dto.getPercentualFrequencia()));

        BoletimItem boletimItemSalvo = boletimItemRepository.save(boletimItem);
        return new BoletimItemResponseDTO(boletimItemSalvo);
    }

    public BoletimItemResponseDTO buscarPorId(Long id) {
        BoletimItem boletimItem = boletimItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item de boletim não encontrado. Id: " + id));
        return new BoletimItemResponseDTO(boletimItem);
    }

    public List<BoletimItemResponseDTO> listarTodos() {
        List<BoletimItem> list = boletimItemRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return list.stream().map(BoletimItemResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public void deletar(Long id) {
        boletimItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item de boletim não encontrado. Id: " + id));

        try {
            boletimItemRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(
                    "Não é possível excluir este item de boletim, pois ele possui registros vinculados!"
            );
        }
    }

    @Transactional
    public BoletimItemResponseDTO atualizar(Long id, BoletimItemRequestDTO dto) {
        BoletimItem boletimItem = boletimItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item de boletim não encontrado. Id: " + id));

        Boletim boletim = boletimRepository.findById(dto.getBoletimId())
                .orElseThrow(() -> new ResourceNotFoundException("Boletim não encontrado. Id: " + dto.getBoletimId()));

        Disciplina disciplina = disciplinaRepository.findById(dto.getDisciplinaId())
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada. Id: " + dto.getDisciplinaId()));

        if (boletimItemRepository.existsByBoletimIdAndDisciplinaIdAndIdNot(boletim.getId(), disciplina.getId(), id)) {
            throw new DatabaseException("Já existe item de boletim para esta disciplina neste boletim!");
        }

        boletimItem.setBoletim(boletim);
        boletimItem.setDisciplina(disciplina);
        boletimItem.setMedia(dto.getMedia());
        boletimItem.setTotalAulas(dto.getTotalAulas());
        boletimItem.setTotalFaltas(dto.getTotalFaltas());
        boletimItem.setPercentualFrequencia(dto.getPercentualFrequencia());
        boletimItem.setSituacao(definirSituacao(dto.getMedia(), dto.getPercentualFrequencia()));

        BoletimItem boletimItemSalvo = boletimItemRepository.save(boletimItem);
        return new BoletimItemResponseDTO(boletimItemSalvo);
    }

    private String definirSituacao(Double media, Double percentualFrequencia) {
        boolean aprovadoPorMedia = media >= MEDIA_MINIMA;
        boolean aprovadoPorFrequencia = percentualFrequencia >= FREQUENCIA_MINIMA;

        if (aprovadoPorMedia && aprovadoPorFrequencia) {
            return "APROVADO";
        }
        if (!aprovadoPorMedia && !aprovadoPorFrequencia) {
            return "REPROVADO_POR_NOTA_E_FALTA";
        }
        if (!aprovadoPorMedia) {
            return "REPROVADO_POR_NOTA";
        }
        return "REPROVADO_POR_FALTA";
    }
}
