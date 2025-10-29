package br.com.serratec.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.serratec.dto.ItemPedidoRequestDTO;
import br.com.serratec.dto.ItemPedidoResponseDTO;
import br.com.serratec.service.ItemPedidoService;

@RestController
@RequestMapping("/itens-pedido")
public class ItemPedidoController {

    private final ItemPedidoService itemPedidoService;

    public ItemPedidoController(ItemPedidoService itemPedidoService) {
        this.itemPedidoService = itemPedidoService;
    }

    @GetMapping
    public ResponseEntity<List<ItemPedidoResponseDTO>> listar() {
        return ResponseEntity.ok(itemPedidoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedidoResponseDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(itemPedidoService.buscar(id));
    }

    @PostMapping
    public ResponseEntity<ItemPedidoResponseDTO> inserir(@RequestBody ItemPedidoRequestDTO dto) {
        return ResponseEntity.ok(itemPedidoService.inserir(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemPedidoResponseDTO> atualizar(@PathVariable Long id, @RequestBody ItemPedidoRequestDTO dto) {
        return ResponseEntity.ok(itemPedidoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        itemPedidoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}