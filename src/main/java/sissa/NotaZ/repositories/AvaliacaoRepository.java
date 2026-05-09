package sissa.NotaZ.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sissa.NotaZ.domain.Avaliacao;

import java.time.LocalDate;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    boolean existsByDisciplinaIdAndNomeAndData(Long disciplinaId, String nome, LocalDate data);

    boolean existsByDisciplinaIdAndNomeAndDataAndIdNot(Long disciplinaId, String nome, LocalDate data, Long id);
}
