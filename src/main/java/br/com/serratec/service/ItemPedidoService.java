package br.com.serratec.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.dto.ItemPedidoRequestDTO;
import br.com.serratec.dto.ItemPedidoResponseDTO;
import br.com.serratec.entity.ItemPedido;
import br.com.serratec.entity.Pedido;
import br.com.serratec.entity.Produto;
import br.com.serratec.repository.ItemPedidoRepository;
import br.com.serratec.repository.PedidoRepository;
import br.com.serratec.repository.ProdutoRepository;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ItemPedidoResponseDTO> listar() {
        return itemPedidoRepository.findAll().stream()
                .map(ItemPedidoResponseDTO::new)
                .collect(Collectors.toList());
    }

    public ItemPedidoResponseDTO buscar(Long id) {
        ItemPedido item = itemPedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item de pedido não encontrado"));
        return new ItemPedidoResponseDTO(item);
    }

    public ItemPedidoResponseDTO inserir(ItemPedidoRequestDTO dto) {
        Pedido pedido = pedidoRepository.findById(dto.getPedidoId())
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        Produto produto = produtoRepository.findById(dto.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        ItemPedido item = new ItemPedido();
        item.setPedido(pedido);
        item.setProduto(produto);
        item.setQuantidade(dto.getQuantidade());
        item.setPrecoUnitario(produto.getPreco());
        item.setSubtotal(produto.getPreco() * dto.getQuantidade());

        return new ItemPedidoResponseDTO(itemPedidoRepository.save(item));
    }

    public ItemPedidoResponseDTO atualizar(Long id, ItemPedidoRequestDTO dto) {
        ItemPedido item = itemPedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item de pedido não encontrado"));

        Pedido pedido = pedidoRepository.findById(dto.getPedidoId())
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        Produto produto = produtoRepository.findById(dto.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        item.setPedido(pedido);
        item.setProduto(produto);
        item.setQuantidade(dto.getQuantidade());
        item.setPrecoUnitario(produto.getPreco());
        item.setSubtotal(produto.getPreco() * dto.getQuantidade());

        return new ItemPedidoResponseDTO(itemPedidoRepository.save(item));
    }

    public void deletar(Long id) {
        itemPedidoRepository.deleteById(id);
    }
}