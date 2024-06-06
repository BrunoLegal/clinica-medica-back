package br.edu.imepac.dtos;

import lombok.Data;

@Data
public class ConvenioCreateRequest {
    private String empresa;
    private String cnpj;
    private String telefone;
}
