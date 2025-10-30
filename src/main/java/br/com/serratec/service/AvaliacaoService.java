package br.com.serratec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.dto.AvaliacaoRequestDTO;
import br.com.serratec.dto.AvaliacaoResponseDTO;
import br.com.serratec.entity.Avaliacao;
import br.com.serratec.entity.Cliente;
import br.com.serratec.entity.Produto;
import br.com.serratec.exception.ResourceNotFoundException;
import br.com.serratec.repository.AvaliacaoRepository;
import br.com.serratec.repository.ClienteRepository;
import br.com.serratec.repository.ProdutoRepository;
import jakarta.transaction.Transactional;

@Service
public class AvaliacaoService {

	@Autowired
	private AvaliacaoRepository avaliacaoRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	public List<AvaliacaoResponseDTO> listar() {
		return avaliacaoRepository.findAll().stream()
				.map(a -> new AvaliacaoResponseDTO(a.getId(), a.getNota(), a.getComentario()))
				.toList();
	}

	public List<AvaliacaoResponseDTO> listarPorProduto(Long idProduto) {
		return avaliacaoRepository.findByProdutoId(idProduto).stream()
				.map(a -> new AvaliacaoResponseDTO(a.getId(), a.getNota(), a.getComentario()))
				.toList();
	}

	@Transactional
	public AvaliacaoResponseDTO inserir(Long idCliente, Long idProduto, AvaliacaoRequestDTO dto) {
		Cliente cliente = clienteRepository.findById(idCliente)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com o ID: " + idCliente));

		Produto produto = produtoRepository.findById(idProduto)
				.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com o ID: " + idProduto));

		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setNota(dto.getNota());
		avaliacao.setComentario(dto.getComentario());
		avaliacao.setCliente(cliente);
		avaliacao.setProduto(produto);

		Avaliacao nova = avaliacaoRepository.save(avaliacao);
		return new AvaliacaoResponseDTO(nova.getId(), nova.getNota(), nova.getComentario());
	}

	@Transactional
	public AvaliacaoResponseDTO atualizar(Long id, AvaliacaoRequestDTO dto) {
		Avaliacao existente = avaliacaoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Avaliação não encontrada com o ID: " + id));

		existente.setNota(dto.getNota());
		existente.setComentario(dto.getComentario());

		Avaliacao atualizada = avaliacaoRepository.save(existente);
		return new AvaliacaoResponseDTO(atualizada.getId(), atualizada.getNota(), atualizada.getComentario());
	}

	@Transactional
	public void deletar(Long id) {
		if (!avaliacaoRepository.existsById(id)) {
			throw new ResourceNotFoundException("Avaliação não encontrada com o ID: " + id);
		}
		avaliacaoRepository.deleteById(id);
	}
}
