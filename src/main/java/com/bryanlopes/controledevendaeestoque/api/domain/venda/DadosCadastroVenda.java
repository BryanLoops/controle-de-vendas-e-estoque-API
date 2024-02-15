package com.bryanlopes.controledevendaeestoque.api.domain.venda;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroVenda(
		
		@NotBlank
		@Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")
		String cpfUsuario,
		@NotBlank
		String codigoProduto,
		@NotNull
		LocalDateTime data,
        @NotNull
        Integer quantidade
		){

}
