package com.bryanlopes.controledevendaeestoque.api.domain.venda;

import com.bryanlopes.controledevendaeestoque.api.domain.produto.Produto;

public record DadosListagemVendas(Long id, String codigoProduto, String cpfUsuario, int quantidade) {
	public DadosListagemVendas(Venda venda) {
		this(venda.getId(), venda.getProduto().getCodigoProduto(), venda.getUsuario().getCpfUsuario(), venda.getQuantidade());
	}
}
