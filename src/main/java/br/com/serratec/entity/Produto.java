package br.com.serratec.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome do produto é obrigatório!")
    private String nome;
    
    private String descricao;
    
    @Min(value = 1, message = "Preço do produto deve ser maior que zero!")
    private Double preco;

    @JsonBackReference
	@ManyToOne
	@NotNull(message = "Produto deve estar vinculado a uma categoria válida!")
	@JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Produto() {
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return this.descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return this.preco;
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}