package com.bryanlopes.controledevendaeestoque.api.domain.usuario;

public record DadosDetalhamentoUsuario(Long id, String nome, String dataDeNascimento, String cpf, String cep) {

	public DadosDetalhamentoUsuario(Usuario usuario) {
		this(usuario.getId(), usuario.getNome(), usuario.getDataDeNascimento(), usuario.getCpfUsuario(), usuario.getCep());
	}
}
