package com.bryanlopes.controledevendaeestoque.api.domain.produto;

import java.util.UUID;

import org.springframework.util.StringUtils;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "produtos")
@Entity(name = "Produto")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String codigoProduto;
	private String nome;
	private Double preco;
	private Boolean ativo;
	
	public Produto(DadosCadastroProduto dados) {
		this.nome = dados.nome();
		this.codigoProduto = criarCodigoUUID();
		this.preco = dados.preco();
		this.ativo = true;
	}
	
	public String criarCodigoUUID() {

	    UUID randomUUID = UUID.randomUUID();

	    return randomUUID.toString().replaceAll("-", "");

	  }

	public void atualizarInformacoes(DadosAtualizacaoProduto dados) {
		if (dados.nome() != null) {
			this.nome = dados.nome();
		}
		if (dados.preco() != null) {
			this.preco = dados.preco();
		}
	}
	
	public void excluir() {
        this.ativo = false;
    }
}
