package com.bryanlopes.controledevendaeestoque.api.domain.usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

	//Nome, data de nascimento, CPF e CEP
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String dataDeNascimento;
	private String cpfUsuario;
	private String cep;
	private Boolean ativo;

	public Usuario(DadosCadastroUsuario dados) {
		this.ativo = true;
		this.nome = dados.nome();
		this.dataDeNascimento = dados.dataDeNascimento();
		this.cpfUsuario = dados.cpf();
		this.cep = dados.cep();
	}

	public void atualizarInformacoes(DadosAtualizacaoUsuario dados) {
		if (dados.nome() != null) {
			this.nome = dados.nome();
		}
		if (dados.dataDeNascimento() != null) {
			this.dataDeNascimento = dados.dataDeNascimento();
		}
		if (dados.cpf() != null) {
			this.cpfUsuario = dados.cpf();
		}
		if (dados.cep() != null) {
			this.cep = dados.cep();
		}
	}

	public void excluir() {
        this.ativo = false;
    }
}
