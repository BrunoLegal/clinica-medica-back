package br.edu.imepac.dtos;

import lombok.Data;

@Data
public class ConvenioDto {
    private Long id;
    private String empresa;
    private String cnpj;
    private  String telefone;
}
