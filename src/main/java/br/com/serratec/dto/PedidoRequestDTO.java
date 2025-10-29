package br.com.serratec.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PedidoRequestDTO {

    @NotNull(message = "O ID do cliente é obrigatório")
    private Long clienteId;

    @NotNull(message = "A lista de itens é obrigatória")
    @Size(min = 1, message = "O pedido deve conter pelo menos 1 item")
    private List<ItemPedidoRequestDTO> itens;

    public PedidoRequestDTO() {
    }

    public Long getClienteId() {
        return this.clienteId;
    }
    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<ItemPedidoRequestDTO> getItens() {
        return this.itens;
    }
    public void setItens(List<ItemPedidoRequestDTO> itens) {
        this.itens = itens;
    }
}