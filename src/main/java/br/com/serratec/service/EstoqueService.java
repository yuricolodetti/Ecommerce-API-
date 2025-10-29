package br.com.serratec.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.serratec.dto.EstoqueCadastroDTO;
import br.com.serratec.entity.Estoque;
import br.com.serratec.entity.Produto;
import br.com.serratec.repository.EstoqueRepository;
import br.com.serratec.repository.ProdutoRepository;
import jakarta.transaction.Transactional;

@Service
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;
    private final ProdutoRepository produtoRepository;

    public EstoqueService(EstoqueRepository estoqueRepository, ProdutoRepository produtoRepository) {
        this.estoqueRepository = estoqueRepository;
        this.produtoRepository = produtoRepository;
    }

    public Optional<Estoque> buscarPorProduto(Long produtoId) {
        return Optional.ofNullable(estoqueRepository.findByProdutoId(produtoId));
    }

    @Transactional
    public Estoque atualizarQuantidade(Long produtoId, Integer novaQtd) {
        Estoque estoque = estoqueRepository.findByProdutoId(produtoId);
        if (estoque == null) {
            throw new RuntimeException("Produto não encontrado no estoque");
        }
        estoque.setQuantidade(novaQtd);
        return estoqueRepository.save(estoque);
    }

    @Transactional
    public Estoque criar(EstoqueCadastroDTO dto) {
        Produto produto = produtoRepository.findById(dto.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        
        Estoque existente = estoqueRepository.findByProdutoId(dto.getProdutoId());
        if (existente != null) {
            throw new RuntimeException("Já existe um estoque para este produto");
        }

        Estoque novo = new Estoque();
        novo.setProduto(produto);
        novo.setQuantidade(dto.getQuantidade());

        return estoqueRepository.save(novo);
    }
}
