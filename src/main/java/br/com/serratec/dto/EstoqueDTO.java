package br.com.serratec.dto;

import java.time.LocalDateTime;

import br.com.serratec.entity.Estoque;

public class EstoqueDTO {

    private Long id;
    private Long produtoId;
    private String produtoNome;
    private Integer quantidade;
    private LocalDateTime ultimaAtualizacao;

    public EstoqueDTO() {}

    public EstoqueDTO(Estoque estoque) {
        this.id = estoque.getId();
        this.produtoId = estoque.getProduto().getId();
        this.produtoNome = estoque.getProduto().getNome();
        this.quantidade = estoque.getQuantidade();
        this.ultimaAtualizacao = estoque.getUltimaAtualizacao();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}

	public String getProdutoNome() {
		return produtoNome;
	}

	public void setProdutoNome(String produtoNome) {
		this.produtoNome = produtoNome;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public LocalDateTime getUltimaAtualizacao() {
		return ultimaAtualizacao;
	}

	public void setUltimaAtualizacao(LocalDateTime ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
	}

    
}
