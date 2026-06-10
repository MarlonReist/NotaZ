package sissa.NotaZ.services;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sissa.NotaZ.domain.*;
import sissa.NotaZ.domain.enums.TipoEnum;
import sissa.NotaZ.dto.ResumoFrequenciaResponseDTO;
import sissa.NotaZ.repositories.AlunoRepository;
import sissa.NotaZ.repositories.AulaRepository;
import sissa.NotaZ.repositories.DisciplinaRepository;
import sissa.NotaZ.repositories.FrequenciaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FrequenciaServiceTest {

    private FrequenciaService frequenciaService;

    private FrequenciaRepository frequenciaRepository;
    private AlunoRepository alunoRepository;
    private AulaRepository aulaRepository;
    private DisciplinaRepository disciplinaRepository;

    @BeforeEach
    void setUp() {
        frequenciaRepository = mock(FrequenciaRepository.class);
        alunoRepository = mock(AlunoRepository.class);
        aulaRepository = mock(AulaRepository.class);
        disciplinaRepository = mock(DisciplinaRepository.class);

        frequenciaService = new FrequenciaService(
                frequenciaRepository,
                alunoRepository,
                aulaRepository,
                disciplinaRepository
        );
    }

    @Test
    void deveCalcularResumoDeFrequenciaEReprovarPorFalta() {
        // ARRANGE
        Long alunoId = 1L;
        Long disciplinaId = 1L;

        Usuario usuarioAluno = new Usuario();
        usuarioAluno.setId(1L);
        usuarioAluno.setNome("Lucao");
        usuarioAluno.setTipo(TipoEnum.ALUNO);
        usuarioAluno.setAtivo(true);

        Aluno aluno = new Aluno();
        aluno.setId(alunoId);
        aluno.setUsuario(usuarioAluno);
        aluno.setMatricula("A001");

        Disciplina disciplina = new Disciplina();
        disciplina.setId(disciplinaId);
        disciplina.setNome("Desenvolvimento");

        Aula aula1 = new Aula();
        aula1.setId(1L);
        aula1.setData(LocalDate.of(2026, 5, 20));
        aula1.setQuantidadeAulas(2);
        aula1.setDisciplina(disciplina);

        Aula aula2 = new Aula();
        aula2.setId(2L);
        aula2.setData(LocalDate.of(2026, 5, 21));
        aula2.setQuantidadeAulas(2);
        aula2.setDisciplina(disciplina);

        Frequencia frequenciaPresente = new Frequencia();
        frequenciaPresente.setId(1L);
        frequenciaPresente.setPresente(true);
        frequenciaPresente.setAluno(aluno);
        frequenciaPresente.setAula(aula1);

        Frequencia frequenciaFalta = new Frequencia();
        frequenciaFalta.setId(2L);
        frequenciaFalta.setPresente(false);
        frequenciaFalta.setAluno(aluno);
        frequenciaFalta.setAula(aula2);

        when(alunoRepository.findById(alunoId))
                .thenReturn(Optional.of(aluno));

        when(disciplinaRepository.findById(disciplinaId))
                .thenReturn(Optional.of(disciplina));

        when(frequenciaRepository.findByAlunoIdAndAulaDisciplinaId(alunoId, disciplinaId))
                .thenReturn(List.of(frequenciaPresente, frequenciaFalta));

        // ACT
        ResumoFrequenciaResponseDTO resultado =
                frequenciaService.calcularResumoAlunoDisciplina(alunoId, disciplinaId);

        // ASSERT
        assertThat(resultado.getAlunoId()).isEqualTo(alunoId);
        assertThat(resultado.getAlunoNome()).isEqualTo("Lucao");
        assertThat(resultado.getDisciplinaId()).isEqualTo(disciplinaId);
        assertThat(resultado.getDisciplinaNome()).isEqualTo("Desenvolvimento");

        assertThat(resultado.getTotalAulas()).isEqualTo(4);
        assertThat(resultado.getTotalPresencas()).isEqualTo(2);
        assertThat(resultado.getTotalFaltas()).isEqualTo(2);

        assertThat(resultado.getPercentualFrequencia()).isCloseTo(50.0, within(0.001));
        assertThat(resultado.getPercentualFaltas()).isCloseTo(50.0, within(0.001));
        assertThat(resultado.getFrequenciaMinima()).isEqualTo(70.0);
        assertThat(resultado.getSituacaoFrequencia()).isEqualTo("REPROVADO_POR_FALTA");
    }
}