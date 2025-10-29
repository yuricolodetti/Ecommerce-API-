package br.com.serratec.dto;

import br.com.serratec.entity.ItemPedido;

public class ItemPedidoResponseDTO {

    private Long id;
    private Long pedidoId;
    private Long produtoId;
    private Integer quantidade;
    private Double precoUnitario;
    private Double subtotal;

    public ItemPedidoResponseDTO() {
    }

    public ItemPedidoResponseDTO(ItemPedido item) {
        this.id = item.getId();
        this.pedidoId = item.getPedido().getId();
        this.produtoId = item.getProduto().getId();
        this.quantidade = item.getQuantidade();
        this.precoUnitario = item.getPrecoUnitario();
        this.subtotal = item.getSubtotal();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(Long pedidoId) {
		this.pedidoId = pedidoId;
	}

	public Long getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(Double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

   
}