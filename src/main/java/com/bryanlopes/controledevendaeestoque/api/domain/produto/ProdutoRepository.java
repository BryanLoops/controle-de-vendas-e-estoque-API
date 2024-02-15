package com.bryanlopes.controledevendaeestoque.api.domain.produto;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jakarta.validation.constraints.NotNull;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	//Produto findByCodigoProduto(String codigoProduto);
	Page<Produto> findAllByAtivoTrue(Pageable paginacao);
    
    Optional<Produto> findByCodigoProduto(String codigoProduto);
	
	@Query("""
            select p.ativo
            from Produto p
            where
            p.id = :id
            """)
	Boolean findAtivoById(Long id);
}
