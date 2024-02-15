package com.bryanlopes.controledevendaeestoque.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.bryanlopes.controledevendaeestoque.api.domain.produto.DadosAtualizacaoProduto;
import com.bryanlopes.controledevendaeestoque.api.domain.produto.DadosCadastroProduto;
import com.bryanlopes.controledevendaeestoque.api.domain.produto.DadosDetalhamentoProduto;
import com.bryanlopes.controledevendaeestoque.api.domain.produto.DadosListagemProduto;
import com.bryanlopes.controledevendaeestoque.api.domain.produto.ProdutoRepository;
import com.bryanlopes.controledevendaeestoque.api.domain.produto.Produto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroProduto dados, UriComponentsBuilder uriBuilder) {
		var produto = new Produto(dados);
		repository.save(produto);
		
		var uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosDetalhamentoProduto(produto));
	}
	
	@GetMapping
	public Page<DadosListagemProduto> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
		return repository.findAllByAtivoTrue(paginacao).map(DadosListagemProduto::new);
	}
	
	@PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoProduto dados) {
		var produto = repository.getReferenceById(dados.id());
		produto.atualizarInformacoes(dados);
	}
	
	@DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
		var produto = repository.getReferenceById(id);
		produto.excluir();
	}
}
