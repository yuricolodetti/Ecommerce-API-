package br.com.serratec.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.serratec.enums.StatusPedido;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataPedido;

    private Double total;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>(); // ✅ Inicialização para evitar NullPointerException

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    public Pedido() {
        // Opcionalmente, garantir que a lista nunca seja nula
        this.itens = new ArrayList<>();
    }

    // Getters e Setters padrão JavaBean
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataPedido() {
        return this.dataPedido;
    }
    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Double getTotal() {
        return this.total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return this.cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedido> getItens() {
        return this.itens;
    }
    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public StatusPedido getStatus() {
        return this.status;
    }
    public void setStatus(StatusPedido status) {
        this.status = status;
    }
}
