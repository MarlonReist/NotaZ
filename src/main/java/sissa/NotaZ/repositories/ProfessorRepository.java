package sissa.NotaZ.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sissa.NotaZ.domain.Aluno;
import sissa.NotaZ.domain.Professor;

import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    boolean existsByRa(String ra);

    boolean existsByUsuarioId(Long usuarioId);

    Optional<Professor> findByRa(String ra);

}
