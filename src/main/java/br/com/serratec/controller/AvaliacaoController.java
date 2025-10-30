package br.com.serratec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.serratec.dto.AvaliacaoRequestDTO;
import br.com.serratec.dto.AvaliacaoResponseDTO;
import br.com.serratec.service.AvaliacaoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

	@Autowired
	private AvaliacaoService avaliacaoService;

	@GetMapping
	public ResponseEntity<List<AvaliacaoResponseDTO>> listar() {
		return ResponseEntity.ok(avaliacaoService.listar());
	}

	@GetMapping("/produtos/{idProduto}")
	public ResponseEntity<List<AvaliacaoResponseDTO>> listarPorProduto(@PathVariable Long idProduto) {
		return ResponseEntity.ok(avaliacaoService.listarPorProduto(idProduto));
	}

	@PostMapping("/clientes/{idCliente}/produtos/{idProduto}")
	public ResponseEntity<AvaliacaoResponseDTO> inserir(@PathVariable Long idCliente, @PathVariable Long idProduto,
			@Valid @RequestBody AvaliacaoRequestDTO dto) {
		return ResponseEntity.ok(avaliacaoService.inserir(idCliente, idProduto, dto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<AvaliacaoResponseDTO> atualizar(@PathVariable Long id,
			@Valid @RequestBody AvaliacaoRequestDTO dto) {
		return ResponseEntity.ok(avaliacaoService.atualizar(id, dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		avaliacaoService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
