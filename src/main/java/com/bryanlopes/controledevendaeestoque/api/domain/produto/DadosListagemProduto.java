package com.bryanlopes.controledevendaeestoque.api.domain.produto;

public record DadosListagemProduto(Long id, String codigo, String nome, Double preco) {
	public DadosListagemProduto(Produto produto) {
		this(produto.getId(), produto.getCodigoProduto(), produto.getNome(), produto.getPreco());
	}
}
