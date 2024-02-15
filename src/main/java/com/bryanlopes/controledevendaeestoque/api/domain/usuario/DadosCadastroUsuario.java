package com.bryanlopes.controledevendaeestoque.api.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroUsuario(
        @NotBlank
        String nome,
        @NotBlank
        @Pattern(regexp = "\\d{2}\\/\\d{2}\\/\\d{4}")
        String dataDeNascimento,
        @NotBlank
        @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")
        String cpf,
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep){
	
}
