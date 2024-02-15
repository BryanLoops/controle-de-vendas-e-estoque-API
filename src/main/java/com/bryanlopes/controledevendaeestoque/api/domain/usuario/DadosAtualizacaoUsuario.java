package com.bryanlopes.controledevendaeestoque.api.domain.usuario;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosAtualizacaoUsuario(
		@NotNull
		Long id,
        String nome,
        @Pattern(regexp = "\\d{2}\\/\\d{2}\\/\\d{4}")
        String dataDeNascimento,
        @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")
        String cpf,
        @Pattern(regexp = "\\d{8}")
        String cep
		) {

}
