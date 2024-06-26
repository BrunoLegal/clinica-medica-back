package br.edu.imepac.repository;

import br.edu.imepac.models.AtendimentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtendimentoRepository extends JpaRepository<AtendimentoModel, Long> {
}
