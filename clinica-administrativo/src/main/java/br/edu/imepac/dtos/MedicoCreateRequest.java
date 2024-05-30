package br.edu.imepac.dtos;

import br.edu.imepac.models.EspecialidadeModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicoCreateRequest {
    private String nome;
    private String crm;
    private EspecialidadeModel especialidade;
}
