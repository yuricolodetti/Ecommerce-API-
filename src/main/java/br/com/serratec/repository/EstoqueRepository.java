package br.com.serratec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.serratec.entity.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
    Estoque findByProdutoId(Long produtoId);
}
