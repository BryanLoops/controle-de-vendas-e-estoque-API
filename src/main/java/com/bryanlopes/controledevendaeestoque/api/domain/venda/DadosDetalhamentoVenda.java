package com.bryanlopes.controledevendaeestoque.api.domain.venda;

import java.time.LocalDateTime;

public record DadosDetalhamentoVenda(
		Long id, 
		//Long idUsuario, 
		//Long idProduto,
		String cpfUsuario,
		String codigoProduto,
		LocalDateTime data, 
		Integer quantidade) {
	public DadosDetalhamentoVenda(Venda venda) {
		this(
				venda.getId(), 
				venda.getUsuario().getCpfUsuario(),
				venda.getProduto().getCodigoProduto(),
				venda.getData(), 
				venda.getQuantidade()
				);
	}
}
