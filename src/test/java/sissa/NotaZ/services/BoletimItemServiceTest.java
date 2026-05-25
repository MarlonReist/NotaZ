package sissa.NotaZ.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sissa.NotaZ.domain.Aluno;
import sissa.NotaZ.domain.Boletim;
import sissa.NotaZ.domain.BoletimItem;
import sissa.NotaZ.domain.Disciplina;
import sissa.NotaZ.domain.Professor;
import sissa.NotaZ.domain.Turma;
import sissa.NotaZ.domain.Usuario;
import sissa.NotaZ.domain.enums.CursoEnum;
import sissa.NotaZ.domain.enums.PeriodoEnum;
import sissa.NotaZ.domain.enums.TipoEnum;
import sissa.NotaZ.dto.BoletimItemRequestDTO;
import sissa.NotaZ.dto.BoletimItemResponseDTO;
import sissa.NotaZ.repositories.BoletimItemRepository;
import sissa.NotaZ.repositories.BoletimRepository;
import sissa.NotaZ.repositories.DisciplinaRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BoletimItemServiceTest {

    private BoletimItemService boletimItemService;

    private BoletimItemRepository boletimItemRepository;
    private BoletimRepository boletimRepository;
    private DisciplinaRepository disciplinaRepository;

    @BeforeEach
    void setUp() {
        boletimItemRepository = mock(BoletimItemRepository.class);
        boletimRepository = mock(BoletimRepository.class);
        disciplinaRepository = mock(DisciplinaRepository.class);

        boletimItemService = new BoletimItemService(
                boletimItemRepository,
                boletimRepository,
                disciplinaRepository
        );
    }

    @Test
    void deveDefinirReprovadoPorNotaEFaltaAoSalvarBoletimItem() {
        // ARRANGE
        Long boletimId = 1L;
        Long disciplinaId = 1L;

        Usuario usuarioAluno = new Usuario();
        usuarioAluno.setId(1L);
        usuarioAluno.setNome("Marlon Reis");
        usuarioAluno.setTipo(TipoEnum.ALUNO);
        usuarioAluno.setAtivo(true);

        Aluno aluno = new Aluno();
        aluno.setId(1L);
        aluno.setUsuario(usuarioAluno);
        aluno.setMatricula("409699");

        Turma turma = new Turma();
        turma.setId(1L);
        turma.setCurso(CursoEnum.SISTEMAS);
        turma.setPeriodo(PeriodoEnum.PRIMEIRO);

        Boletim boletim = new Boletim();
        boletim.setId(boletimId);
        boletim.setPeriodo("1 Bimestre");
        boletim.setDataFechamento(LocalDate.of(2026, 5, 30));
        boletim.setAluno(aluno);
        boletim.setTurma(turma);

        Usuario usuarioProfessor = new Usuario();
        usuarioProfessor.setId(2L);
        usuarioProfessor.setNome("Professor Teste");
        usuarioProfessor.setTipo(TipoEnum.PROFESSOR);
        usuarioProfessor.setAtivo(true);

        Professor professor = new Professor();
        professor.setId(1L);
        professor.setRa("P001");
        professor.setUsuario(usuarioProfessor);

        Disciplina disciplina = new Disciplina();
        disciplina.setId(disciplinaId);
        disciplina.setNome("Desenvolvimento de Software");
        disciplina.setProfessor(professor);

        BoletimItemRequestDTO dto = new BoletimItemRequestDTO();
        dto.setBoletimId(boletimId);
        dto.setDisciplinaId(disciplinaId);
        dto.setMedia(5.5);
        dto.setTotalAulas(20);
        dto.setTotalFaltas(8);
        dto.setPercentualFrequencia(60.0);

        when(boletimRepository.findById(boletimId))
                .thenReturn(Optional.of(boletim));

        when(disciplinaRepository.findById(disciplinaId))
                .thenReturn(Optional.of(disciplina));

        when(boletimItemRepository.existsByBoletimIdAndDisciplinaId(boletimId, disciplinaId))
                .thenReturn(false);

        when(boletimItemRepository.save(any(BoletimItem.class)))
                .thenAnswer(invocation -> {
                    BoletimItem boletimItem = invocation.getArgument(0);
                    boletimItem.setId(1L);
                    return boletimItem;
                });

        // ACT
        BoletimItemResponseDTO resultado = boletimItemService.salvar(dto);

        // ASSERT
        assertThat(resultado.getId()).isEqualTo(1L);
        assertThat(resultado.getBoletimId()).isEqualTo(boletimId);
        assertThat(resultado.getBoletimPeriodo()).isEqualTo("1 Bimestre");
        assertThat(resultado.getDisciplinaId()).isEqualTo(disciplinaId);
        assertThat(resultado.getDisciplinaNome()).isEqualTo("Desenvolvimento de Software");
        assertThat(resultado.getMedia()).isEqualTo(5.5);
        assertThat(resultado.getTotalAulas()).isEqualTo(20);
        assertThat(resultado.getTotalFaltas()).isEqualTo(8);
        assertThat(resultado.getPercentualFrequencia()).isEqualTo(60.0);
        assertThat(resultado.getSituacao()).isEqualTo("REPROVADO_POR_NOTA_E_FALTA");
    }
}
