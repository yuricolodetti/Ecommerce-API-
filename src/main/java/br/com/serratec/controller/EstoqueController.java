package br.com.serratec.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.dto.EstoqueCadastroDTO;
import br.com.serratec.dto.EstoqueDTO;
import br.com.serratec.entity.Estoque;
import br.com.serratec.service.EstoqueService;

@RestController
@RequestMapping("/estoques")
public class EstoqueController {

    private final EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @GetMapping("/{produtoId}")
    public ResponseEntity<EstoqueDTO> buscar(@PathVariable Long produtoId) {
        return estoqueService.buscarPorProduto(produtoId)
                .map(estoque -> ResponseEntity.ok(new EstoqueDTO(estoque)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EstoqueDTO> criar(@RequestBody EstoqueCadastroDTO dto) {
        Estoque novo = estoqueService.criar(dto);
        return ResponseEntity.ok(new EstoqueDTO(novo));
    }

    @PutMapping("/{produtoId}/{novaQtd}")
    public ResponseEntity<EstoqueDTO> atualizar(@PathVariable Long produtoId, @PathVariable Integer novaQtd) {
        Estoque atualizado = estoqueService.atualizarQuantidade(produtoId, novaQtd);
        return ResponseEntity.ok(new EstoqueDTO(atualizado));
    }
}

