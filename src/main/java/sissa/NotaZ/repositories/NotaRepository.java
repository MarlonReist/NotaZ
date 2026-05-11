package sissa.NotaZ.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sissa.NotaZ.domain.Nota;

import java.util.List;

public interface NotaRepository extends JpaRepository<Nota, Long> {

    boolean existsByAlunoIdAndAvaliacaoId(Long alunoId, Long avaliacaoId);

    boolean existsByAlunoIdAndAvaliacaoIdAndIdNot(Long alunoId, Long avaliacaoId, Long id);

    List<Nota> findByAlunoIdAndAvaliacaoDisciplinaId(Long alunoId, Long disciplinaId);
}
