package com.bryanlopes.controledevendaeestoque.api.domain.usuario;

import java.time.LocalDateTime;
import java.util.List;

import com.bryanlopes.controledevendaeestoque.api.domain.produto.Produto;
import com.bryanlopes.controledevendaeestoque.api.domain.venda.DadosDetalhamentoVenda;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RelatorioUsuarioDTO {
    private Usuario usuario;
    private EnderecoViaCep endereco;
    private List<DadosDetalhamentoVenda> vendas;
}
