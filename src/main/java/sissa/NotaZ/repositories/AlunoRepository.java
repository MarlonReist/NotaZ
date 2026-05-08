package sissa.NotaZ.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sissa.NotaZ.domain.Aluno;
import sissa.NotaZ.domain.Usuario;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    boolean existsByMatricula(String matricula);

    boolean existsByUsuarioId(Long usuarioId);

    Optional<Aluno> findByMatricula(String matricula);

}
