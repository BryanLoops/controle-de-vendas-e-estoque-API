package com.bryanlopes.controledevendaeestoque.api.domain.estoque;

import java.time.LocalDateTime;

import com.bryanlopes.controledevendaeestoque.api.domain.produto.Produto;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "estoque")
@Entity(name = "Estoque")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Estoque {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id")
	private Produto produto;
	
	private LocalDateTime data;
	private String nome;
	private Integer quantidade;
	private Boolean ativo;
	
	
}