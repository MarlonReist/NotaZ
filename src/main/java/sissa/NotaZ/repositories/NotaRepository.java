package sissa.NotaZ.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sissa.NotaZ.domain.Nota;

public interface NotaRepository extends JpaRepository<Nota, Long> {

    boolean existsByAlunoIdAndAvaliacaoId(Long alunoId, Long avaliacaoId);

    boolean existsByAlunoIdAndAvaliacaoIdAndIdNot(Long alunoId, Long avaliacaoId, Long id);

}
