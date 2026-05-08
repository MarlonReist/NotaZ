package sissa.NotaZ.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sissa.NotaZ.domain.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
}
