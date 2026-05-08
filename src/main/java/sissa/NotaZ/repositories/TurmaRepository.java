package sissa.NotaZ.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sissa.NotaZ.domain.Turma;
import sissa.NotaZ.domain.enums.CursoEnum;
import sissa.NotaZ.domain.enums.PeriodoEnum;

import java.util.Optional;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
    boolean existsByCursoAndPeriodo(CursoEnum curso, PeriodoEnum periodo);

    Optional<Turma> findByCursoAndPeriodo(CursoEnum curso, PeriodoEnum periodo);
}
