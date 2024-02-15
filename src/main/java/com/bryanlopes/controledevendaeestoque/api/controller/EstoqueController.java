package com.bryanlopes.controledevendaeestoque.api.controller;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bryanlopes.controledevendaeestoque.api.domain.estoque.DadosCadastroEstoque;
import com.bryanlopes.controledevendaeestoque.api.domain.estoque.DadosDetalhamentoEstoque;
import com.bryanlopes.controledevendaeestoque.api.domain.estoque.Estoque;
import com.bryanlopes.controledevendaeestoque.api.domain.estoque.EstoqueRepository;
import com.bryanlopes.controledevendaeestoque.api.domain.produto.Produto;
import com.bryanlopes.controledevendaeestoque.api.domain.produto.ProdutoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("estoque")
public class EstoqueController {
	
	@Autowired
	private EstoqueRepository estoqueRepository;
	
	@Autowired 
	private ProdutoRepository produtoRepository;
	
	@PostMapping
	@Transactional
	public DadosDetalhamentoEstoque cadastrar(@RequestBody @Valid DadosCadastroEstoque dados) {
	    java.util.Optional<Produto> produtoOptional = produtoRepository.findById(dados.idProduto());

	    Produto produto = produtoOptional.get();

	    Estoque estoque = new Estoque(
	        null,
	        produto,
	        dados.data(),
	        "Estoque %s - %s%s%s".formatted(produto.getNome(), produto.getId(), ThreadLocalRandom.current().nextInt(0, 9999 + 1), dados.quantidade()),
	        dados.quantidade(),
	        true
	    );

	    Estoque savedEstoque = estoqueRepository.save(estoque);

	    return new DadosDetalhamentoEstoque(savedEstoque);
	}

}
