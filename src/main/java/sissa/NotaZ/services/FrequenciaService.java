package sissa.NotaZ.services;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sissa.NotaZ.domain.Aluno;
import sissa.NotaZ.domain.Aula;
import sissa.NotaZ.domain.Disciplina;
import sissa.NotaZ.domain.Frequencia;
import sissa.NotaZ.dto.FrequenciaRequestDTO;
import sissa.NotaZ.dto.FrequenciaResponseDTO;
import sissa.NotaZ.dto.ResumoFrequenciaResponseDTO;
import sissa.NotaZ.repositories.AlunoRepository;
import sissa.NotaZ.repositories.AulaRepository;
import sissa.NotaZ.repositories.DisciplinaRepository;
import sissa.NotaZ.repositories.FrequenciaRepository;
import sissa.NotaZ.services.exceptions.DatabaseException;
import sissa.NotaZ.services.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FrequenciaService {

    private static final double FREQUENCIA_MINIMA = 70.0;

    private final FrequenciaRepository frequenciaRepository;
    private final AlunoRepository alunoRepository;
    private final AulaRepository aulaRepository;
    private final DisciplinaRepository disciplinaRepository;

    public FrequenciaService(FrequenciaRepository frequenciaRepository, AlunoRepository alunoRepository, AulaRepository aulaRepository, DisciplinaRepository disciplinaRepository) {
        this.frequenciaRepository = frequenciaRepository;
        this.alunoRepository = alunoRepository;
        this.aulaRepository = aulaRepository;
        this.disciplinaRepository = disciplinaRepository;
    }

    @Transactional
    public FrequenciaResponseDTO salvar(FrequenciaRequestDTO dto) {
        Aluno aluno = alunoRepository.findById(dto.getAlunoId())
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado. Id: " + dto.getAlunoId()));

        Aula aula = aulaRepository.findById(dto.getAulaId())
                .orElseThrow(() -> new ResourceNotFoundException("Aula não encontrada. Id: " + dto.getAulaId()));

        if (frequenciaRepository.existsByAlunoIdAndAulaId(aluno.getId(), aula.getId())) {
            throw new DatabaseException("Já existe frequência para este aluno nesta aula!");
        }

        Frequencia frequencia = new Frequencia();
        frequencia.setPresente(dto.getPresente());
        frequencia.setAluno(aluno);
        frequencia.setAula(aula);

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

        Aula aula = aulaRepository.findById(dto.getAulaId())
                .orElseThrow(() -> new ResourceNotFoundException("Aula não encontrada. Id: " + dto.getAulaId()));

        if (frequenciaRepository.existsByAlunoIdAndAulaIdAndIdNot(aluno.getId(), aula.getId(),  id)) {
            throw new DatabaseException("Já existe frequência para este aluno nesta aula!");
        }

        frequencia.setPresente(dto.getPresente());
        frequencia.setAluno(aluno);
        frequencia.setAula(aula);

        Frequencia frequenciaSalva = frequenciaRepository.save(frequencia);
        return new FrequenciaResponseDTO(frequenciaSalva);
    }

    public ResumoFrequenciaResponseDTO calcularResumoAlunoDisciplina(Long alunoId, Long disciplinaId) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado. Id: " + alunoId));

        Disciplina disciplina = disciplinaRepository.findById(disciplinaId)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada. Id: " + disciplinaId));

        List<Frequencia> frequencias = frequenciaRepository.findByAlunoIdAndAulaDisciplinaId(alunoId, disciplinaId);

        if (frequencias.isEmpty()) {
            throw new ResourceNotFoundException("Nenhuma frequência encontrada para este aluno nesta disciplina.");
        }

        int totalAulas = 0;
        int totalPresencas = 0;
        int totalFaltas = 0;

        for (Frequencia frequencia : frequencias) {
            int quantidadeAulas = frequencia.getAula().getQuantidadeAulas();
            totalAulas += quantidadeAulas;

            if (Boolean.TRUE.equals(frequencia.getPresente())) {
                totalPresencas += quantidadeAulas;
            } else {
                totalFaltas += quantidadeAulas;
            }
        }

        double percentualFrequencia = (totalPresencas * 100.0) / totalAulas;
        double percentualFaltas = (totalFaltas * 100.0) / totalAulas;
        String situacaoFrequencia = percentualFrequencia >= FREQUENCIA_MINIMA ? "FREQUENCIA_OK" : "REPROVADO_POR_FALTA";

        return new ResumoFrequenciaResponseDTO(
                aluno.getId(),
                aluno.getUsuario().getNome(),
                disciplina.getId(),
                disciplina.getNome(),
                totalAulas,
                totalPresencas,
                totalFaltas,
                percentualFrequencia,
                percentualFaltas,
                FREQUENCIA_MINIMA,
                situacaoFrequencia
        );
    }
}
