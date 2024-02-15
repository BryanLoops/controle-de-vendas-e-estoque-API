package com.bryanlopes.controledevendaeestoque.api.domain.venda;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bryanlopes.controledevendaeestoque.api.domain.usuario.Usuario;

public interface VendaRepository extends JpaRepository<Venda, Long> {
	Page<Venda> findAllByAtivoTrue(Pageable paginacao);
	
	@Query("""
            select v.ativo
            from Venda v
            where
            v.id = :id
            """)
    Boolean findAtivoById(Long id);

	Page<Venda> findAllByUsuario(Usuario usuario, Pageable pageable);
}
