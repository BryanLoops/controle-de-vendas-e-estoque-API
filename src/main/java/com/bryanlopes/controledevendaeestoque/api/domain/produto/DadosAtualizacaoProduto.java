package com.bryanlopes.controledevendaeestoque.api.domain.produto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoProduto(
		@NotNull
		Long id,
		String nome,
		Double preco
		) {

}
