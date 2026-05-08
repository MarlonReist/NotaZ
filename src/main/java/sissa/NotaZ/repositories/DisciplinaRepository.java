package sissa.NotaZ.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sissa.NotaZ.domain.Disciplina;

import java.util.Optional;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    boolean existsByProfessorIdAndNome(Long professorId, String nome);

    Optional<Disciplina> findByProfessorIdAndNome(Long professorId, String nome);
}
