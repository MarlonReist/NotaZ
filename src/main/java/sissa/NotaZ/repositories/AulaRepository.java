package sissa.NotaZ.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sissa.NotaZ.domain.Aula;

import java.time.LocalDate;

public interface AulaRepository extends JpaRepository<Aula, Long> {

    boolean existsByTurmaIdAndDisciplinaIdAndData(Long turmaId, Long disciplinaId, LocalDate data);

    boolean existsByTurmaIdAndDisciplinaIdAndDataAndIdNot(Long turmaId, Long disciplinaId, LocalDate data, Long id);
}
