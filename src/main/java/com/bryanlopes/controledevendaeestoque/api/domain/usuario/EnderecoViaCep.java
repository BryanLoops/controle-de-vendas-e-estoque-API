package com.bryanlopes.controledevendaeestoque.api.domain.usuario;

import java.time.LocalDateTime;

import com.bryanlopes.controledevendaeestoque.api.domain.produto.Produto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoViaCep {
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;

}
