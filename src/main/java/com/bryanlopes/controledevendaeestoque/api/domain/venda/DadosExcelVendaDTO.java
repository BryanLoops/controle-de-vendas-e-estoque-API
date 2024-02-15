package com.bryanlopes.controledevendaeestoque.api.domain.venda;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DadosExcelVendaDTO {

	private String codigoProduto;
	private String cpfUsuario;
	private int quantidade;
	private LocalDateTime data;
	
	
}
