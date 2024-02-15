package com.bryanlopes.controledevendaeestoque.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.bryanlopes.controledevendaeestoque.api.domain.usuario.DadosAtualizacaoUsuario;
import com.bryanlopes.controledevendaeestoque.api.domain.usuario.DadosCadastroUsuario;
import com.bryanlopes.controledevendaeestoque.api.domain.usuario.DadosDetalhamentoUsuario;
import com.bryanlopes.controledevendaeestoque.api.domain.usuario.DadosListagemUsuario;
import com.bryanlopes.controledevendaeestoque.api.domain.usuario.EnderecoViaCep;
import com.bryanlopes.controledevendaeestoque.api.domain.usuario.RelatorioUsuarioDTO;
import com.bryanlopes.controledevendaeestoque.api.domain.usuario.UsuarioRepository;
import com.bryanlopes.controledevendaeestoque.api.domain.venda.DadosDetalhamentoVenda;
import com.bryanlopes.controledevendaeestoque.api.domain.venda.Venda;
import com.bryanlopes.controledevendaeestoque.api.domain.venda.VendaRepository;
import com.bryanlopes.controledevendaeestoque.api.domain.usuario.Usuario;

import jakarta.validation.Valid;


@RestController
@RequestMapping("usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private VendaRepository vendaRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder) {
		var usuario = new Usuario(dados);
		usuarioRepository.save(usuario);
		
		var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario));
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemUsuario>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
		var page = usuarioRepository.findAllByAtivoTrue(paginacao).map(DadosListagemUsuario::new);
		return ResponseEntity.ok(page);
	}
	
	@GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {	
		try {
			var usuario = usuarioRepository.getReferenceById(id);
			
			if (usuarioRepository.findAtivoById(id)) {	
		        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch(Exception e) {
			System.out.println(e);
			return ResponseEntity.badRequest().build();
		}
    }
	
	@PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoUsuario dados) {
		var usuario = usuarioRepository.getReferenceById(dados.id());
		usuario.atualizarInformacoes(dados);
	}
	
	@DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
		var usuario = usuarioRepository.getReferenceById(id);
		usuario.excluir();
	}
	
	@GetMapping("/relatorio/{cpf}")
    public ResponseEntity<RelatorioUsuarioDTO> gerarRelatorioPorCPF(@PathVariable String cpf, Pageable pageable) {

        Optional<Usuario> usuarioOpt = usuarioRepository.findByCpfUsuario(cpf);
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Usuario usuario = usuarioOpt.get();
        String cep = usuario.getCep();
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        RestTemplate restTemplate = new RestTemplate();
        EnderecoViaCep endereco = restTemplate.getForObject(url, EnderecoViaCep.class);

        Page<Venda> vendasUsuario = vendaRepository.findAllByUsuario(usuario, pageable);

        RelatorioUsuarioDTO relatorio = new RelatorioUsuarioDTO(
            usuario,
            endereco,
            vendasUsuario.stream().map(DadosDetalhamentoVenda::new).collect(Collectors.toList())
        );

        return ResponseEntity.ok(relatorio);
    }
}
