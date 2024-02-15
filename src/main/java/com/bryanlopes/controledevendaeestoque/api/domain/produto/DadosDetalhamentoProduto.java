package com.bryanlopes.controledevendaeestoque.api.domain.produto;

public record DadosDetalhamentoProduto(Long id, String codigo, String nome, Double preco) {
	public DadosDetalhamentoProduto(Produto produto) {
		this(produto.getId(), produto.getCodigoProduto(), produto.getNome(), produto.getPreco());
	}
}
