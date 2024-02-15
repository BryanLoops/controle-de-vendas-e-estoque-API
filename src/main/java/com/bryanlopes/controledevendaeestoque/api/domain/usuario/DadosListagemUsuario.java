package com.bryanlopes.controledevendaeestoque.api.domain.usuario;

public record DadosListagemUsuario(Long id, String nome, String dataDeNascimento, String cpf, String cep) {
	public DadosListagemUsuario(Usuario usuario) {
		this(usuario.getId(), usuario.getNome(), usuario.getDataDeNascimento(), usuario.getCpfUsuario(), usuario.getCep());
	}
}
