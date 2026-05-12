package sissa.NotaZ.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sissa.NotaZ.domain.BoletimItem;

public interface BoletimItemRepository extends JpaRepository<BoletimItem, Long> {

    boolean existsByBoletimIdAndDisciplinaId(Long boletimId, Long disciplinaId);

    boolean existsByBoletimIdAndDisciplinaIdAndIdNot(Long boletimId, Long disciplinaId, Long id);
}
