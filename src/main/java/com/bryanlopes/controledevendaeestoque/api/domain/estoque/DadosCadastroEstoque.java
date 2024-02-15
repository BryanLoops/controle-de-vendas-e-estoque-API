package com.bryanlopes.controledevendaeestoque.api.domain.estoque;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroEstoque(
		@NotNull
		Long idProduto,
		String nome,
		@NotNull
		LocalDateTime data,
        @NotNull
        Integer quantidade
		) {

}
