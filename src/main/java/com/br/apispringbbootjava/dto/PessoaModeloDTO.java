package com.br.apispringbbootjava.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PessoaModeloDTO {

    private String nome;
    private Integer idade;
    private String email;
    private String cidade;
    
}
