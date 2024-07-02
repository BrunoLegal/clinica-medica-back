package br.edu.imepac.dtos;

import br.edu.imepac.models.AgendaConsulta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtendimentoDto {
    private Long id;
    private AgendaConsulta idAgenda;
    private String historico;
    private String receituario;
    private String exames;
}
