package br.com.serratec.dto;

import br.com.serratec.entity.Avaliacao;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class AvaliacaoRequestDTO {
	
	private Long id;
	
	@Min(1)
    @Max(5)
    private Integer nota;
    private String comentario;
    private Long idProduto;
    private String nomeProduto;
    private Long idCliente;
    private String nomeCliente;
    
    public AvaliacaoRequestDTO() {
    }
    
    
    
    public AvaliacaoRequestDTO(Avaliacao avaliacao) {
        this.id = avaliacao.getId();
        this.nota = avaliacao.getNota();
        this.comentario = avaliacao.getComentario();
        this.idProduto = avaliacao.getProduto().getId();
        this.nomeProduto = avaliacao.getProduto().getNome();
        this.idCliente = avaliacao.getCliente().getId();
        this.nomeCliente = avaliacao.getCliente().getNome();
    }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getNota() {
		return nota;
	}
	public void setNota(Integer nota) {
		this.nota = nota;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Long getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
    
    
    

}
