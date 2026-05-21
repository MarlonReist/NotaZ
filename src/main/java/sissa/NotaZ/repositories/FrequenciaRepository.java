package sissa.NotaZ.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sissa.NotaZ.domain.Frequencia;

import java.util.List;

public interface FrequenciaRepository extends JpaRepository<Frequencia, Long> {

    boolean existsByAlunoIdAndAulaId(Long alunoId, Long aulaId);

    boolean existsByAlunoIdAndAulaIdAndIdNot(Long alunoId, Long aulaId, Long id);

    List<Frequencia> findByAlunoIdAndAulaDisciplinaId(Long alunoId, Long disciplinaId);

    List<Frequencia> findByAulaId(Long aulaId);
}
