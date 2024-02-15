package com.bryanlopes.controledevendaeestoque.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bryanlopes.controledevendaeestoque.api.domain.produto.ProdutoRepository;
import com.bryanlopes.controledevendaeestoque.api.domain.usuario.UsuarioRepository;
import com.bryanlopes.controledevendaeestoque.api.domain.venda.DadosCadastroVenda;
import com.bryanlopes.controledevendaeestoque.api.domain.venda.DadosDetalhamentoVenda;
import com.bryanlopes.controledevendaeestoque.api.domain.venda.DadosExcelVendaDTO;
import com.bryanlopes.controledevendaeestoque.api.domain.venda.DadosListagemProdutosVendidos;
import com.bryanlopes.controledevendaeestoque.api.domain.venda.DadosListagemVendas;
import com.bryanlopes.controledevendaeestoque.api.domain.venda.Venda;
import com.bryanlopes.controledevendaeestoque.api.domain.venda.VendaRepository;
import com.bryanlopes.controledevendaeestoque.api.domain.usuario.Usuario;
import com.bryanlopes.controledevendaeestoque.api.domain.estoque.Estoque;
import com.bryanlopes.controledevendaeestoque.api.domain.estoque.EstoqueRepository;
import com.bryanlopes.controledevendaeestoque.api.domain.produto.DadosListagemProduto;
import com.bryanlopes.controledevendaeestoque.api.domain.produto.Produto;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("vendas")
public class VendasController {
	
	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstoqueRepository estoqueRepository;
	
	@PostMapping
	@Transactional
	public DadosDetalhamentoVenda cadastrar(@RequestBody @Valid DadosCadastroVenda dados) {
		//Optional<Usuario> usuarioOptional = usuarioRepository.findByCpf(dados.cpfUsuario());
		//Optional<Produto> produtoOptional = produtoRepository.findByCodigo(dados.codigoProduto());
		
		//System.out.println(usuarioOptional.get());
		//System.out.println(produtoOptional.get());
		
		Optional<Usuario> usuarioOpt = usuarioRepository.findByCpfUsuario(dados.cpfUsuario());
		Optional<Produto> produtoOpt = produtoRepository.findByCodigoProduto(dados.codigoProduto());
		
		Usuario usuario = usuarioOpt.get();
		Produto produto = produtoOpt.get();
		
		//System.out.println(usuario);
		//System.out.println(produto);
		
		Venda venda = new Venda(
				null,
				usuario,
				produto,
				dados.data(),
				dados.quantidade(),
				true
		);
		
		Venda vendaConclusao = vendaRepository.save(venda);
		
		return new DadosDetalhamentoVenda(vendaConclusao);
	}
	
	@PostMapping("/upload")
	@Transactional
    public ResponseEntity<String> uploadExcelFile(@RequestParam("file") MultipartFile file) {
        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());

            Sheet sheet = workbook.getSheetAt(0);

            List<DadosExcelVendaDTO> dataList = new ArrayList<>();

            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getRowNum() == 0)
                    continue;

                String codigoProduto = row.getCell(0).getStringCellValue();
                String cpfUsuario = row.getCell(1).getStringCellValue();
                int quantidade = (int) row.getCell(2).getNumericCellValue();
                LocalDateTime data = row.getCell(3).getLocalDateTimeCellValue();

                DadosExcelVendaDTO dados = new DadosExcelVendaDTO(codigoProduto, cpfUsuario, quantidade, data);
                dataList.add(dados);
                
                System.out.println(dados.getCpfUsuario());
                System.out.println(dados.getCodigoProduto());
                
                Optional<Usuario> usuarioOpt = usuarioRepository.findByCpfUsuario(dados.getCpfUsuario());
        		Optional<Produto> produtoOpt = produtoRepository.findByCodigoProduto(dados.getCodigoProduto());
        		
        		Usuario usuario = usuarioOpt.get();
        		Produto produto = produtoOpt.get();
        		
        		//System.out.println(usuario);
        		//System.out.println(produto);
        		
        		Venda venda = new Venda(
        				null,
        				usuario,
        				produto,
        				dados.getData(),
        				dados.getQuantidade(),
        				true
        		);
        		
        		vendaRepository.save(venda);

            }

            return ResponseEntity.status(HttpStatus.OK).body("Arquivo carregado com suacesso.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao carregar arquivo.");
        }
    }
	
	@GetMapping
	public List<DadosListagemProdutosVendidos> listarProdutosVendidos(@PageableDefault Pageable paginacao) {
	    List<DadosListagemProdutosVendidos> produtosVendidos = new ArrayList<>();

	    Page<Venda> vendas = vendaRepository.findAllByAtivoTrue(paginacao);

	    Map<YearMonth, Integer> quantidadeTotalVendidaPorMes = new HashMap<>();

	    Map<YearMonth, Integer> quantidadeTotalEstoquePorMes = new HashMap<>();

	    for (Venda venda : vendas) {
	        YearMonth mesVenda = YearMonth.from(venda.getData());
	        int quantidade = venda.getQuantidade();
	        quantidadeTotalVendidaPorMes.put(mesVenda, quantidadeTotalVendidaPorMes.getOrDefault(mesVenda, 0) + quantidade);
	    }

	    List<Estoque> estoques = estoqueRepository.findAll();
	    for (Estoque estoque : estoques) {
	        YearMonth mesEstoque = YearMonth.from(estoque.getData());
	        int quantidade = estoque.getQuantidade();
	        quantidadeTotalEstoquePorMes.put(mesEstoque, quantidadeTotalEstoquePorMes.getOrDefault(mesEstoque, 0) + quantidade);
	    }

	    for (Map.Entry<YearMonth, Integer> entry : quantidadeTotalVendidaPorMes.entrySet()) {
	        YearMonth mes = entry.getKey();
	        int quantidadeTotalVendida = entry.getValue();
	        int quantidadeTotalEstoque = quantidadeTotalEstoquePorMes.getOrDefault(mes, 0);

	        String status;
	        if (quantidadeTotalVendida > quantidadeTotalEstoque) {
	            status = "QTD_DIVERGENTE";
	        } else if (quantidadeTotalVendida == quantidadeTotalEstoque) {
	            status = "OK";
	        } else {

	            int totalEstoqueDisponivel = quantidadeTotalEstoque - quantidadeTotalVendida;

	            int vinteCincoPorCentoEstoque = (int) (quantidadeTotalEstoque * 0.25);
	            if (totalEstoqueDisponivel < vinteCincoPorCentoEstoque) {
	                status = "BAIXA_DEMANDA";
	            } else {
	                status = "OK";
	            }
	        }

	        List<DadosDetalhamentoVenda> detalhesVendas = vendas.stream()
	            .filter(venda -> YearMonth.from(venda.getData()).equals(mes))
	            .map(DadosDetalhamentoVenda::new)
	            .collect(Collectors.toList());

	        DadosListagemProdutosVendidos produtoVendido = new DadosListagemProdutosVendidos(mes.toString(), quantidadeTotalVendida, detalhesVendas, status);
	        produtosVendidos.add(produtoVendido);
	    }

	    return produtosVendidos;
	}
}
