package com.bryanlopes.controledevendaeestoque.api.domain.estoque;

import java.time.LocalDateTime;

public record DadosDetalhamentoEstoque(Long id, Long produto_id, String nome, Integer quantidade, LocalDateTime data) {
	public DadosDetalhamentoEstoque(Estoque estoque) {
		this(estoque.getId(), estoque.getProduto().getId(), estoque.getNome(), estoque.getQuantidade(), estoque.getData());
	}
}
