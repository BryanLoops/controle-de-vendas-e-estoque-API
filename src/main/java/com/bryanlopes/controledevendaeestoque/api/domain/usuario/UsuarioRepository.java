package com.bryanlopes.controledevendaeestoque.api.domain.usuario;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bryanlopes.controledevendaeestoque.api.domain.produto.Produto;

import jakarta.validation.constraints.NotNull;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	//Usuario findByCpfUsuario(String cpfUsuario);
	Page<Usuario> findAllByAtivoTrue(Pageable paginacao);

	Optional<Usuario> findByCpfUsuario(String cpfUsuario);
	
	@Query("""
            select u.ativo
            from Usuario u
            where
            u.id = :id
            """)
    Boolean findAtivoById(Long id);
}
