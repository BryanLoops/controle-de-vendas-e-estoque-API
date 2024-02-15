package com.bryanlopes.controledevendaeestoque.api.domain.venda;

import java.util.ArrayList;
import java.util.List;

public record DadosListagemProdutosVendidos(String mes, int quantidadeTotalVendida, List<DadosDetalhamentoVenda> detalhesVendas, String status) {
    public DadosListagemProdutosVendidos(String mes, int quantidadeTotalVendida, List<DadosDetalhamentoVenda> detalhesVendas, String status) {
        this.mes = mes;
        this.quantidadeTotalVendida = quantidadeTotalVendida;
        this.detalhesVendas = detalhesVendas;
        this.status = status;
    }
}

