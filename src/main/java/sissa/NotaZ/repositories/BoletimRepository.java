package sissa.NotaZ.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sissa.NotaZ.domain.Boletim;

public interface BoletimRepository extends JpaRepository<Boletim, Long> {

    boolean existsByAlunoIdAndTurmaIdAndPeriodo(Long alunoId, Long turmaId, String periodo);

    boolean existsByAlunoIdAndTurmaIdAndPeriodoAndIdNot(Long alunoId, Long turmaId, String periodo, Long id);
}
