package br.com.serratec.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.serratec.entity.Pedido;
import br.com.serratec.entity.ItemPedido;

public class PedidoResponseDTO {

    private Long id;
    private LocalDateTime dataPedido;
    private Double total;
    private String status;
    private Long clienteId;
    private List<ItemPedidoResponseDTO> itens;

    public PedidoResponseDTO() {
    }

    public PedidoResponseDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.dataPedido = pedido.getDataPedido();
        this.total = pedido.getTotal();
        this.status = pedido.getStatus().name();
        this.clienteId = pedido.getCliente().getId();
        this.itens = pedido.getItens().stream()
                .map(ItemPedidoResponseDTO::new) 
                .collect(Collectors.toList());
    }

    public Long getId() { return this.id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDataPedido() { return this.dataPedido; }
    public void setDataPedido(LocalDateTime dataPedido) { this.dataPedido = dataPedido; }

    public Double getTotal() { return this.total; }
    public void setTotal(Double total) { this.total = total; }

    public String getStatus() { return this.status; }
    public void setStatus(String status) { this.status = status; }

    public Long getClienteId() { return this.clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }

    public List<ItemPedidoResponseDTO> getItens() { return this.itens; }
    public void setItens(List<ItemPedidoResponseDTO> itens) { this.itens = itens; }
}