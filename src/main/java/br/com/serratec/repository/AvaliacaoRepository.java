package br.com.serratec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.entity.Avaliacao;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long>{
	
	List<Avaliacao> findByProdutoId(Long idProduto);

}
