package sissa.NotaZ.services;

import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sissa.NotaZ.domain.Aluno;
import sissa.NotaZ.domain.Boletim;
import sissa.NotaZ.domain.Turma;
import sissa.NotaZ.dto.BoletimRequestDTO;
import sissa.NotaZ.dto.BoletimResponseDTO;
import sissa.NotaZ.repositories.AlunoRepository;
import sissa.NotaZ.repositories.BoletimRepository;
import sissa.NotaZ.repositories.TurmaRepository;
import sissa.NotaZ.services.exceptions.DatabaseException;
import sissa.NotaZ.services.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoletimService {

    private final BoletimRepository boletimRepository;
    private final AlunoRepository alunoRepository;
    private final TurmaRepository turmaRepository;

    public BoletimService(BoletimRepository boletimRepository, AlunoRepository alunoRepository, TurmaRepository turmaRepository) {
        this.boletimRepository = boletimRepository;
        this.alunoRepository = alunoRepository;
        this.turmaRepository = turmaRepository;
    }

    @Transactional
    public BoletimResponseDTO salvar(BoletimRequestDTO dto) {
        Aluno aluno = alunoRepository.findById(dto.getAlunoId())
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado. Id: " + dto.getAlunoId()));

        if (!aluno.getUsuario().isAtivo()) {
            throw new DatabaseException("Aluno informado está inativo!");
        }

        Turma turma = turmaRepository.findById(dto.getTurmaId())
                .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada. Id: " + dto.getTurmaId()));

        if (boletimRepository.existsByAlunoIdAndTurmaIdAndPeriodo(aluno.getId(), turma.getId(), dto.getPeriodo())) {
            throw new DatabaseException("Já existe boletim para este aluno nesta turma e neste período!");
        }

        Boletim boletim = new Boletim();
        boletim.setPeriodo(dto.getPeriodo());
        boletim.setDataFechamento(dto.getDataFechamento());
        boletim.setMediaGeral(dto.getMediaGeral());
        boletim.setPercentualFrequencia(dto.getPercentualFrequencia());
        boletim.setSituacao(dto.getSituacao());
        boletim.setAluno(aluno);
        boletim.setTurma(turma);

        Boletim boletimSalvo = boletimRepository.save(boletim);
        return new BoletimResponseDTO(boletimSalvo);
    }

    public BoletimResponseDTO buscarPorId(Long id) {
        Boletim boletim = boletimRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Boletim não encontrado. Id: " + id));
        return new BoletimResponseDTO(boletim);
    }

    @Transactional
    public void deletar(Long id) {
        boletimRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Boletim não encontrado. Id: " + id));

        try {
            boletimRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(
                    "Não é possível excluir este Boletim, pois ele possui registros vinculados!"
            );
        }
    }

    public List<BoletimResponseDTO> listarTodos() {
        List<Boletim> list = boletimRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return list.stream().map(BoletimResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public BoletimResponseDTO atualizar(Long id, BoletimRequestDTO dto) {
        Boletim boletim = boletimRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Boletim não encontrado. Id: " + id));

        Aluno aluno = alunoRepository.findById(dto.getAlunoId())
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado. Id: " + dto.getAlunoId()));

        if (!aluno.getUsuario().isAtivo()) {
            throw new DatabaseException("Aluno informado está inativo!");
        }

        Turma turma = turmaRepository.findById(dto.getTurmaId())
                .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada. Id: " + dto.getTurmaId()));

        if (boletimRepository.existsByAlunoIdAndTurmaIdAndPeriodoAndIdNot(aluno.getId(), turma.getId(), dto.getPeriodo(), id)) {
            throw new DatabaseException("Já existe boletim para este aluno nesta turma e neste período!");
        }

        boletim.setPeriodo(dto.getPeriodo());
        boletim.setDataFechamento(dto.getDataFechamento());
        boletim.setMediaGeral(dto.getMediaGeral());
        boletim.setPercentualFrequencia(dto.getPercentualFrequencia());
        boletim.setSituacao(dto.getSituacao());
        boletim.setAluno(aluno);
        boletim.setTurma(turma);

        Boletim boletimSalvo = boletimRepository.save(boletim);
        return new BoletimResponseDTO(boletimSalvo);
    }
}
