package br.com.serratec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.dto.ProdutoRequestDTO;
import br.com.serratec.dto.ProdutoResponseDTO;
import br.com.serratec.entity.Categoria;
import br.com.serratec.entity.Produto;
import br.com.serratec.repository.CategoriaRepository;
import br.com.serratec.repository.ProdutoRepository;
import jakarta.transaction.Transactional;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<ProdutoResponseDTO> listar() {
		return produtoRepository.findAll().stream().map(ProdutoResponseDTO::new).toList();
	}

	public ProdutoResponseDTO buscar(Long id) {
		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Produto não encontrado"));
		return new ProdutoResponseDTO(produto);
	}

	@Transactional
	public ProdutoResponseDTO inserir(ProdutoRequestDTO dto) {
		Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
				.orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

		Produto produto = new Produto();
		produto.setNome(dto.getNome());
		produto.setDescricao(dto.getDescricao());
		produto.setPreco(dto.getPreco());
		produto.setCategoria(categoria);

		return new ProdutoResponseDTO(produtoRepository.save(produto));
	}

	@Transactional
	public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO dto) {
		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Produto não encontrado"));

		Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
				.orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

		produto.setNome(dto.getNome());
		produto.setDescricao(dto.getDescricao());
		produto.setPreco(dto.getPreco());
		produto.setCategoria(categoria);

		return new ProdutoResponseDTO(produtoRepository.save(produto));
	}

	@Transactional
	public void deletar(Long id) {
		produtoRepository.deleteById(id);
	}
}