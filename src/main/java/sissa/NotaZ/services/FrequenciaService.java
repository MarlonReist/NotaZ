package sissa.NotaZ.services;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sissa.NotaZ.domain.Aluno;
import sissa.NotaZ.domain.Disciplina;
import sissa.NotaZ.domain.Frequencia;
import sissa.NotaZ.dto.FrequenciaRequestDTO;
import sissa.NotaZ.dto.FrequenciaResponseDTO;
import sissa.NotaZ.repositories.AlunoRepository;
import sissa.NotaZ.repositories.DisciplinaRepository;
import sissa.NotaZ.repositories.FrequenciaRepository;
import sissa.NotaZ.services.exceptions.DatabaseException;
import sissa.NotaZ.services.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FrequenciaService {

    private final FrequenciaRepository frequenciaRepository;
    private final AlunoRepository alunoRepository;
    private final DisciplinaRepository disciplinaRepository;

    public FrequenciaService(FrequenciaRepository frequenciaRepository, AlunoRepository alunoRepository, DisciplinaRepository disciplinaRepository) {
        this.frequenciaRepository = frequenciaRepository;
        this.alunoRepository = alunoRepository;
        this.disciplinaRepository = disciplinaRepository;
    }

    @Transactional
    public FrequenciaResponseDTO salvar(FrequenciaRequestDTO dto) {
        Aluno aluno = alunoRepository.findById(dto.getAlunoId())
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado. Id: " + dto.getAlunoId()));

        Disciplina disciplina = disciplinaRepository.findById(dto.getDisciplinaId())
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada. Id: " + dto.getDisciplinaId()));

        if (frequenciaRepository.existsByAlunoIdAndDisciplinaIdAndData(aluno.getId(), disciplina.getId(), dto.getData())) {
            throw new DatabaseException("Já existe frequência para este aluno nesta disciplina e com essa data");
        }

        Frequencia frequencia = new Frequencia();
        frequencia.setData(dto.getData());
        frequencia.setPresente(dto.getPresente());
        frequencia.setAluno(aluno);
        frequencia.setDisciplina(disciplina);

        Frequencia frequenciaSalva = frequenciaRepository.save(frequencia);
        return new FrequenciaResponseDTO(frequenciaSalva);
    }

    public FrequenciaResponseDTO buscarPorId(Long id) {
        Frequencia frequencia = frequenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Frequência não encontrada. Id: " + id));
        return new FrequenciaResponseDTO(frequencia);
    }

    public List<FrequenciaResponseDTO> listarTodos() {
        List<Frequencia> list = frequenciaRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return list.stream().map(FrequenciaResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public void deletar(Long id) {
        frequenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Frequência não encontrada. Id: " + id));

        frequenciaRepository.deleteById(id);
    }

    @Transactional
    public FrequenciaResponseDTO atualizar(Long id, FrequenciaRequestDTO dto){
        Frequencia frequencia = frequenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Frequência não encontrada. Id: " + id));

        Aluno aluno = alunoRepository.findById(dto.getAlunoId())
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado. Id: " + dto.getAlunoId()));

        Disciplina disciplina = disciplinaRepository.findById(dto.getDisciplinaId())
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada. Id: " + dto.getDisciplinaId()));

        if (frequenciaRepository.existsByAlunoIdAndDisciplinaIdAndDataAndIdNot(aluno.getId(), disciplina.getId(), dto.getData(),  id)) {
            throw new DatabaseException("Já existe frequência para este aluno nesta disciplina e com essa data");
        }

        frequencia.setData(dto.getData());
        frequencia.setPresente(dto.getPresente());
        frequencia.setAluno(aluno);
        frequencia.setDisciplina(disciplina);

        Frequencia frequenciaSalva = frequenciaRepository.save(frequencia);
        return new FrequenciaResponseDTO(frequenciaSalva);
    }
}
