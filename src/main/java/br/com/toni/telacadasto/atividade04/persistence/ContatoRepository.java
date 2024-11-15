package br.com.toni.telacadasto.atividade04.persistence;

import br.com.toni.telacadasto.atividade04.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {
}
