package br.edu.imepac.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtendimentoCreateRequest {
    private Long pacienteId;
    private Long medicoId;
    private LocalDateTime dataHora;
    private String descricao;
}
