package sissa.NotaZ.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sissa.NotaZ.domain.Frequencia;

import java.time.LocalDate;

public interface FrequenciaRepository extends JpaRepository<Frequencia, Long> {

    boolean existsByAlunoIdAndDisciplinaIdAndData(Long alunoId, Long disciplinaId, LocalDate data);

    boolean existsByAlunoIdAndDisciplinaIdAndDataAndIdNot(Long alunoId, Long disciplinaId, LocalDate data, Long id);
}
