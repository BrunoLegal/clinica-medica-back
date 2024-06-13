package br.edu.imepac.repositories;

import br.edu.imepac.models.AgendaConsulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Optional;

@Repository
public interface AgendaRepository extends JpaRepository<AgendaConsulta, Long> {
    Optional<AgendaConsulta> findByDataAndHora(Date data, String Hora);
}
