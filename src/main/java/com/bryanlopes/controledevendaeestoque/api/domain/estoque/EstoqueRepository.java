package com.bryanlopes.controledevendaeestoque.api.domain.estoque;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
	
	Page<Estoque> findAllByAtivoTrue(Pageable paginacao);
	
	@Query("""
            select e.ativo
            from Estoque e
            where
            e.id = :id
            """)
    Boolean findAtivoById(Long id);
}
