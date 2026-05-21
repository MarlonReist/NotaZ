package sissa.NotaZ.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sissa.NotaZ.domain.*;
import sissa.NotaZ.domain.enums.TipoEnum;
import sissa.NotaZ.dto.MediaAlunoDisciplinaResponseDTO;
import sissa.NotaZ.dto.NotaRequestDTO;
import sissa.NotaZ.repositories.AlunoRepository;
import sissa.NotaZ.repositories.AvaliacaoRepository;
import sissa.NotaZ.repositories.DisciplinaRepository;
import sissa.NotaZ.repositories.NotaRepository;
import sissa.NotaZ.services.exceptions.DatabaseException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class NotaServiceTest {

    private NotaService notaService;

    private NotaRepository notaRepository;
    private AlunoRepository alunoRepository;
    private AvaliacaoRepository avaliacaoRepository;
    private DisciplinaRepository disciplinaRepository;

    @BeforeEach
    void setUp() {
        notaRepository = mock(NotaRepository.class);
        alunoRepository = mock(AlunoRepository.class);
        avaliacaoRepository = mock(AvaliacaoRepository.class);
        disciplinaRepository = mock(DisciplinaRepository.class);

        notaService = new NotaService(
                notaRepository,
                alunoRepository,
                avaliacaoRepository,
                disciplinaRepository);
    }

    @Test
    void deveCalcularMediaPonderadaDoAlunoNaDisciplina() {
        // ARRANGE
        Long alunoId = 1L;
        Long disciplinaId = 1L;

        Usuario usuarioAluno = new Usuario();
        usuarioAluno.setId(1L);
        usuarioAluno.setNome("Cleiton Rastafari");
        usuarioAluno.setTipo(TipoEnum.ALUNO);
        usuarioAluno.setAtivo(true);

        Aluno aluno = new Aluno();
        aluno.setId(alunoId);
        aluno.setUsuario(usuarioAluno);
        aluno.setMatricula("A001");

        Disciplina disciplina = new Disciplina();
        disciplina.setId(disciplinaId);
        disciplina.setNome("Matematica");

        Avaliacao avaliacao1 = new Avaliacao();
        avaliacao1.setId(1L);
        avaliacao1.setNome("Prova 1");
        avaliacao1.setPeso(2.0);
        avaliacao1.setData(LocalDate.of(2026, 5, 20));
        avaliacao1.setDisciplina(disciplina);

        Avaliacao avaliacao2 = new Avaliacao();
        avaliacao2.setId(2L);
        avaliacao2.setNome("Trabalho");
        avaliacao2.setPeso(1.0);
        avaliacao2.setData(LocalDate.of(2026, 5, 21));
        avaliacao2.setDisciplina(disciplina);

        Nota nota1 = new Nota();
        nota1.setId(1L);
        nota1.setValorNota(8.0);
        nota1.setAluno(aluno);
        nota1.setAvaliacao(avaliacao1);

        Nota nota2 = new Nota();
        nota2.setId(2L);
        nota2.setValorNota(10.0);
        nota2.setAluno(aluno);
        nota2.setAvaliacao(avaliacao2);

        when(alunoRepository.findById(alunoId))
                .thenReturn(Optional.of(aluno));

        when(disciplinaRepository.findById(disciplinaId))
                .thenReturn(Optional.of(disciplina));

        when(notaRepository.findByAlunoIdAndAvaliacaoDisciplinaId(alunoId, disciplinaId))
                .thenReturn(List.of(nota1, nota2));
        // ACT
        MediaAlunoDisciplinaResponseDTO resultado =
                notaService.calcularMediaAlunoDisciplina(alunoId, disciplinaId);

        // ASSERT
        assertThat(resultado.getAlunoId()).isEqualTo(alunoId);
        assertThat(resultado.getAlunoNome()).isEqualTo("Cleiton Rastafari");
        assertThat(resultado.getDisciplinaId()).isEqualTo(disciplinaId);
        assertThat(resultado.getDisciplinaNome()).isEqualTo("Matematica");
        assertThat(resultado.getMedia()).isCloseTo(8.666, within(0.001));
        assertThat(resultado.getTotalPeso()).isEqualTo(3.0);
        assertThat(resultado.getQuantidadeNotas()).isEqualTo(2);
    }

    @Test
    void deveLancarExcecaoAoSalvarNotaDuplicada() {
        Long alunoId = 2L;
        Long disciplinaId = 2L;
        Long avaliacaoId = 1L;

        // ARRANGE
        Usuario usuarioAluno = new Usuario();
        usuarioAluno.setId(2L);
        usuarioAluno.setNome("Marlon Reis");
        usuarioAluno.setTipo(TipoEnum.ALUNO);
        usuarioAluno.setAtivo(true);

        Disciplina disciplina = new Disciplina();
        disciplina.setId(disciplinaId);
        disciplina.setNome("Desenvolvimento de Software");

        Aluno aluno = new Aluno();
        aluno.setId(alunoId);
        aluno.setUsuario(usuarioAluno);
        aluno.setMatricula("409699");

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setId(avaliacaoId);
        avaliacao.setNome("Prova 1");
        avaliacao.setPeso(7.0);
        avaliacao.setData(LocalDate.of(2026, 5, 25));
        avaliacao.setDisciplina(disciplina);

        NotaRequestDTO dto = new NotaRequestDTO();
        dto.setValorNota(9.0);
        dto.setAlunoId(alunoId);
        dto.setAvaliacaoId(avaliacaoId);

        when(alunoRepository.findById(alunoId))
                .thenReturn(Optional.of(aluno));

        when(avaliacaoRepository.findById(avaliacaoId))
                .thenReturn(Optional.of(avaliacao));

        when(notaRepository.existsByAlunoIdAndAvaliacaoId(alunoId, avaliacaoId))
                .thenReturn(true);

        // ACT -- ASSERT - nesse teste eles ficam juntos, porque esperamos uma exceção
        assertThatThrownBy(() -> notaService.salvar(dto))
                .isInstanceOf(DatabaseException.class)
                .hasMessage("Já existe uma nota lançada para esse aluno nessa avaliação!");

        verify(notaRepository, never()).save(any());
    }
}
