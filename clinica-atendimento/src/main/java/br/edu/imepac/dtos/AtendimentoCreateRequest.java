package br.edu.imepac.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtendimentoCreateRequest {
    @NotNull
    private Long pacienteId;
    @NotNull
    private Long medicoId;
    @NotNull
    private LocalDateTime dataHora;
    @NotNull
    private String descricao;
}
