package com.br.apispringbbootjava.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tb_pessoa")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O campo nome não pode ser vazio")
    @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
    private String nome;
    @Email(message = "O e-mail informado não é válido")
    private String email;
    @NotBlank(message = "O campo cidade não pode ser vazio")
    @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
    private String cidade;
    @NotNull(message = "Idade inválidade")
    @Min(value = 0, message = "Idade minima é 0")
    @Max(value = 120, message = "Idade maxima é 120")
    private Integer idade;
}
